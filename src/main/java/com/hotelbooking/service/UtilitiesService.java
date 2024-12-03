package com.hotelbooking.service;

import com.hotelbooking.dto.request.UtilitiesCreationRequest;
import com.hotelbooking.dto.request.UtilitiesUpdateRequest;
import com.hotelbooking.dto.response.UtilitiesResponse;
import com.hotelbooking.entity.Utilities;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.mapper.UtilitiesMapper;
import com.hotelbooking.repository.UtilitiesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UtilitiesService {

    UtilitiesMapper utilitiesMapper;
    UtilitiesRepository utilitiesRepository;

    public UtilitiesResponse createUtilities(UtilitiesCreationRequest request) {
        Utilities utilities = utilitiesMapper.toUtilities(request);
        utilities = utilitiesRepository.save(utilities);

        return utilitiesMapper.toUtilitiesResponse(utilities);
    }

    public List<UtilitiesResponse> getListUtilities() {
        log.info("In method get list Utilities");
        return utilitiesRepository.findAll().stream().map(utilitiesMapper::toUtilitiesResponse).toList();
    }

    public UtilitiesResponse getUtilities(String id) {
        return utilitiesMapper.toUtilitiesResponse(
                utilitiesRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UTILITIES_NOT_FOUND)));
    }

    public UtilitiesResponse updateUtilities(String utilitiesId, UtilitiesUpdateRequest request) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(() -> new AppException(ErrorCode.UTILITIES_NOT_FOUND));

        utilitiesMapper.updateUtilities(utilities, request);

        return utilitiesMapper.toUtilitiesResponse(utilitiesRepository.save(utilities));
    }

    public void deleteUtilities(String utilitiesId) {
        utilitiesRepository.deleteById(utilitiesId);
    }
}
