package com.example.gnb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    public void addCorsMapping(CorsRegistry registry){
//        registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("GET");
////        registry.addMapping("/product").allowedOrigins("http://localhost:3000").allowCredentials(true);
////        registry.addMapping("/expense").allowedOrigins("http://localhost:3000").allowCredentials(true);
//    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
