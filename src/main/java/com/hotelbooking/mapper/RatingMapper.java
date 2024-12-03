package com.hotelbooking.mapper;

import com.hotelbooking.dto.request.RatingCreationRequest;
import com.hotelbooking.dto.request.RatingUpdateRequest;
import com.hotelbooking.dto.response.RatingResponse;
import com.hotelbooking.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    Rating toRating(RatingCreationRequest request);
    RatingResponse toRatingResponse(Rating rating);
    void updateRating(@MappingTarget Rating rating, RatingUpdateRequest request);
}
