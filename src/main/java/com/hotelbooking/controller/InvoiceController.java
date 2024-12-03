package com.hotelbooking.controller;

import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.dto.response.InvoiceResponse;
import com.hotelbooking.service.InvoiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceController {
    InvoiceService invoiceService;

    @GetMapping
    ApiResponse<List<InvoiceResponse>> getInvoices() {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(invoiceService.getInvoices())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<List<InvoiceResponse>> getInvoicesByUserId(@PathVariable("userId") String userId) {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(invoiceService.getInvoicesByUserId(userId))
                .build();
    }

    @GetMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> getInvoice(@PathVariable("invoiceId") String invoiceId) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(invoiceService.getInvoice(invoiceId))
                .build();
    }

    @DeleteMapping("/{invoiceId}")
    ApiResponse<String> deleteInvoice(@PathVariable String invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ApiResponse.<String>builder().result("Invoice has been deleted").build();
    }
}
