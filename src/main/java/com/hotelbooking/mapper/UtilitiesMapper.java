package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.UtilitiesCreationRequest;
import com.hotelbooking.dto.request.UtilitiesUpdateRequest;
import com.hotelbooking.dto.response.UtilitiesResponse;
import com.hotelbooking.entity.Utilities;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UtilitiesMapper {
    Utilities toUtilities(UtilitiesCreationRequest request);
    UtilitiesResponse toUtilitiesResponse(Utilities utilities);
    void updateUtilities(@MappingTarget Utilities utilities, UtilitiesUpdateRequest request);
}
