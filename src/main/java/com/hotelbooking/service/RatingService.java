package com.hotelbooking.service;

import com.hotelbooking.dto.request.RatingCreationRequest;
import com.hotelbooking.dto.request.RatingUpdateRequest;
import com.hotelbooking.dto.response.RatingResponse;
import com.hotelbooking.entity.Hotel;
import com.hotelbooking.entity.Rating;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.RatingMapper;
import com.hotelbooking.repository.HotelRepository;
import com.hotelbooking.repository.RatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingService {

    RatingMapper ratingMapper;
    RatingRepository ratingRepository;
    HotelRepository hotelRepository;

    public RatingResponse createRating(RatingCreationRequest request) {
        Rating rating = ratingMapper.toRating(request);
        rating.setCreatedAt(LocalDate.now());
        rating = ratingRepository.save(rating);

        updateRatingHotel(rating.getId());

        return ratingMapper.toRatingResponse(rating);
    }

    public List<RatingResponse> getRatings() {
        log.info("In method get Ratings");
        return ratingRepository.findAll().stream().map(ratingMapper::toRatingResponse).toList();
    }

    public List<RatingResponse> getRatingsByHotel(String hotelId) {
        return ratingRepository.findAllByHotelId(hotelId).stream().map(ratingMapper::toRatingResponse).toList();
    }

    public RatingResponse getRating(String id) {
        return ratingMapper.toRatingResponse(
                ratingRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RATINGS_NOT_FOUND)));
    }

    public RatingResponse updateRating(String ratingId, RatingUpdateRequest request) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new AppException(ErrorCode.RATINGS_NOT_FOUND));

        ratingMapper.updateRating(rating, request);

        updateRatingHotel(rating.getId());

        return ratingMapper.toRatingResponse(ratingRepository.save(rating));
    }

    public void deleteRating(String ratingId) {
        updateRatingHotel(ratingId);
        ratingRepository.deleteById(ratingId);
    }

    private void updateRatingHotel(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new AppException(ErrorCode.RATINGS_NOT_FOUND));
        Hotel hotel = hotelRepository.findById(rating.getHotelId()).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));
        List<Rating> ratings = ratingRepository.findAllByHotelId(hotel.getId());
        Double rate = 0.0;
        for (Rating r : ratings) {
            rate += r.getPoint();
        }
        rate = rate / ratings.size();
        hotel.setRating(rate);
        hotelRepository.save(hotel);
    }
}
