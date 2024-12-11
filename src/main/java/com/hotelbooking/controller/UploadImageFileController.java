package com.hotelbooking.controller;

import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.service.UploadImageFileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/uploadImages")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UploadImageFileController {
    UploadImageFileService uploadImageFileService;

    @PostMapping
    ApiResponse<List<String>> uploadImages(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String imageUrl = uploadImageFileService.uploadImage(file);
            uploadedUrls.add(imageUrl);
        }
        return ApiResponse.<List<String>>builder()
                .result(uploadedUrls)
                .build();
    }
}
