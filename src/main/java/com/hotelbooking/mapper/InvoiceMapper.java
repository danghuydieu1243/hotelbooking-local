package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.InvoiceCreationRequest;
import com.hotelbooking.dto.response.InvoiceResponse;
import com.hotelbooking.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(InvoiceCreationRequest request);
    InvoiceResponse toInvoiceResponse(Invoice invoice);
}
