package com.hotelbooking.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary configKey() {
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "dfolztuvq");
        config.put("api_key", "991354464386767");
        config.put("api_secret", "QgwVvFtpuxVG62mstKKiHH5yZ7k");
        return new Cloudinary(config);
    }
}
