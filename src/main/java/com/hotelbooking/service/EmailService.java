package com.hotelbooking.service;

import com.hotelbooking.dto.request.MailBody;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailService {
    final JavaMailSender javaMailSender;

//    public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }

    public void sendSimpleMessage(MailBody mailBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom("uteshop1243@gmail.com");
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        javaMailSender.send(message);
    }
}
