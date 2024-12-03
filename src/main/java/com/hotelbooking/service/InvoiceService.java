package com.hotelbooking.service;

import com.hotelbooking.dto.request.InvoiceCreationRequest;
import com.hotelbooking.dto.response.InvoiceResponse;
import com.hotelbooking.entity.Invoice;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.InvoiceMapper;
import com.hotelbooking.repository.InvoiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceService {
    InvoiceRepository invoiceRepository;
    InvoiceMapper invoiceMapper;

    public InvoiceResponse createInvoice(InvoiceCreationRequest request) {
        Invoice invoice = invoiceMapper.toInvoice(request);
        invoice.setCreationDate(LocalDate.now());
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toInvoiceResponse(invoice);
    }

    public void deleteInvoice(String invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    public List<InvoiceResponse> getInvoices() {
        log.info("In method get Invoices");
        return invoiceRepository.findAll().stream().map(invoiceMapper::toInvoiceResponse).toList();
    }

    public InvoiceResponse getInvoice(String id) {
        return invoiceMapper.toInvoiceResponse(
                invoiceRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_FOUND)));
    }

    public List<InvoiceResponse> getInvoicesByUserId(String userId) {
        log.info("In method get InvoicesByUserId");
        return invoiceRepository.findInvoiceByUserId(userId).stream().map(invoiceMapper::toInvoiceResponse).toList();
    }
}
