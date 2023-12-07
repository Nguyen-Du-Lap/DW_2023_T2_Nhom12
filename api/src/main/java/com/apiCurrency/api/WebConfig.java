package com.apiCurrency.api;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Ánh xạ CORS cho các endpoint cụ thể, bạn có thể đặt đường dẫn tùy ý
                .allowedOrigins("http://localhost:3000")  // Cho phép yêu cầu từ domain này
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Cho phép các phương thức HTTP cụ thể
                .allowedHeaders("*");  // Cho phép tất cả các headers
    }
}
