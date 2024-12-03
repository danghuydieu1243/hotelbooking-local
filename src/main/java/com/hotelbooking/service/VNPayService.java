package com.hotelbooking.service;

import com.hotelbooking.common.BookingStatus;
import com.hotelbooking.common.PaymentStatus;
import com.hotelbooking.configuration.VNPayConfig;
import com.hotelbooking.dto.request.InvoiceCreationRequest;
import com.hotelbooking.entity.Booking;
import com.hotelbooking.entity.Room;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.repository.BookingRepository;
import com.hotelbooking.repository.RoomRepository;
import com.hotelbooking.util.VNPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VNPayService {
    VNPayConfig vnPayConfig;

    HttpServletRequest request;

    VNPayUtils vnPayUtils;

    BookingRepository bookingRepository;

    RoomRepository roomRepository;

    InvoiceService invoiceService;


    //    public boolean validatePayment(Map<String, String> params) {
//        String vnpSecureHash = params.remove("vnp_SecureHash");
//        String generatedSignature = vnPayUtils.hmacSHA512(vnPayConfig.getHashSecret(),params.toString());
//
//        return vnpSecureHash.equalsIgnoreCase(generatedSignature);
//    }
    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }

    public String createPaymentRequest(String bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        Room room = roomRepository.findById(booking.getRoomId()).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        String bankCode = request.getParameter("bankCode");

        String vnp_TmnCode = vnPayConfig.getTmnCode();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);

        long  temp = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        double totalAmount = Double.parseDouble(room.getPrice()) * temp;

        vnp_Params.put("vnp_Amount", String.format("%.0f", totalAmount  * 100)); // VNPay yêu cầu số tiền tính bằng đồng
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", booking.getId());
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + booking.getId());
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = request.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", vnPayConfig.getVnpReturnUrl());


        vnp_Params.put("vnp_IpAddr", getIpAddress(request));

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.HOUR, 8);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());

        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = vnPayUtils.hmacSHA512(vnPayConfig.getHashSecret(), hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = vnPayConfig.getVnpUrl()+ "?" + queryUrl;
        return paymentUrl;
    }

    public String handlePaymentCallback(Map<String, String> params){
        Map<String, String> fields = new HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String fieldName = URLEncoder.encode(entry.getKey(), StandardCharsets.US_ASCII);
            String fieldValue = URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        Booking booking = bookingRepository.findById(params.get("vnp_TxnRef")).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        Room room = roomRepository.findById(booking.getRoomId()).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        Double totalAmount = Double.parseDouble(params.get("vnp_Amount"));

        String vnp_SecureHash = params.get("vnp_SecureHash");
        System.out.println(vnp_SecureHash);
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = vnPayUtils.hashAllFields(fields);
        System.out.println(signValue);
//        if (signValue.equals(vnp_SecureHash) && params.get("vnp_ResponseCode").equals("00"))
//            return "Success";
//        else return "Failed";

        InvoiceCreationRequest invoice = InvoiceCreationRequest.builder()
                .userId(booking.getCustomerId())
                .build();
        if(signValue.equals(vnp_SecureHash))
        {
            if (params.get("vnp_ResponseCode").equals("00"))
            {
                invoice.setTotalAmount(totalAmount);
                invoice.setPaymentStatus(PaymentStatus.SUCCESS);
                invoiceService.createInvoice(invoice);
                booking.setStatus(BookingStatus.PAID.toString());
                bookingRepository.save(booking);
                return "Thanh toán thành công!";
            }
            else if (params.get("vnp_ResponseCode").equals("05"))
            {
                invoice.setPaymentStatus(PaymentStatus.FAILED);
                invoiceService.createInvoice(invoice);
                invoiceService.createInvoice(invoice);
                booking.setStatus(BookingStatus.FAILED.toString());
                bookingRepository.save(booking);
                return "Tài khoản không đủ số dư!";
            }
            else
            {
                invoice.setPaymentStatus(PaymentStatus.FAILED);
                invoiceService.createInvoice(invoice);
                invoiceService.createInvoice(invoice);
                booking.setStatus(BookingStatus.FAILED.toString());
                bookingRepository.save(booking);
                return "Thanh toán thất bại!";
            }
        }
        return "Thanh toán thất bại!";
    }
}
