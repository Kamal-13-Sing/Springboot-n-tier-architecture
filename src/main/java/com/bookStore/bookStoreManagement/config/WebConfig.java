package com.bookStore.bookStoreManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // This method configures CORS (Cross-Origin Resource Sharing) settings for the application
    public void addCorsMappings(CorsRegistry registry) {
        // Add CORS mappings for all endpoints
        registry.addMapping("/**") // This pattern allows access to all API endpoints in the application
                .allowedOrigins("http://localhost:3000") // Specify which origins are allowed to access the resources. Here, only requests from http://localhost:3000 (the React frontend) are permitted
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // List of HTTP methods that are allowed for CORS requests
                .allowedHeaders("*") // Allow all headers in the request. This means the client can send any custom headers.
                .allowCredentials(true); // Allows credentials (such as cookies or HTTP authentication) to be sent with the requests. This is necessary if your frontend needs to authenticate or maintain a session with the backend.
    }


}
