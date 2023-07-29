package com.softwaremanager.schedulebuilder.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPiConfig {
    
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Schedule builder API")
                .description("Application to help managers keeping tracks of Employee Hours")
                .version("v1.0.0"));
                
                 
                
                
                
    }

}
