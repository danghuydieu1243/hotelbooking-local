package com.hotelbooking.controller;

import com.hotelbooking.dto.request.MailBody;
import com.hotelbooking.dto.response.ApiResponse;
import com.hotelbooking.entity.ForgotPassword;
import com.hotelbooking.entity.User;
import com.hotelbooking.exception.AppException;
import com.hotelbooking.exception.ErrorCode;
import com.hotelbooking.repository.ForgotPasswordRepository;
import com.hotelbooking.repository.UserRepository;
import com.hotelbooking.service.EmailService;
import com.hotelbooking.util.ChangePassword;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ForgotPasswordController {

    UserRepository userRepository;
    EmailService emailService;
    ForgotPasswordRepository forgotPasswordRepository;
    PasswordEncoder passwordEncoder;

    @PostMapping("/verifyMail/{email}")
    ApiResponse<String> verifyMail(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        int otp = otpGenerator();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 *1000))
                .user(user)
                .build();

        MailBody mailBody = MailBody.builder()
                .to(email)
                .subject("OTP for forgot Password")
                .text("This is the OTP for your forgot Password request: "+ otp)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(forgotPassword);

        return ApiResponse.<String>builder().result(" Email sent for verification ! ").build();
    }

    Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ApiResponse<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new AppException(ErrorCode.INVALID_OTP));

        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.delete(fp);
            return ApiResponse.<String>builder()
                    .result("OTP has expired!")
                    .build();
        }

        return ApiResponse.<String>builder()
                .result(" OTP verified ! ")
                .build();
    }

    @PostMapping("/changePassword/{email}")
    public ApiResponse<String> changePassword(@RequestBody ChangePassword changePassword, @PathVariable String email) {

        if(!Objects.equals(changePassword.password(),changePassword.repeatPassword())){
            return ApiResponse.<String>builder()
                    .result("Passwords do not match!")
                    .build();
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);

        return ApiResponse.<String>builder()
                .result(" Password changed! ")
                .build();
    }
}
