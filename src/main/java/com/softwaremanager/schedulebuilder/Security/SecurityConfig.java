package com.softwaremanager.schedulebuilder.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.softwaremanager.schedulebuilder.Security.Filter.AuthenticationFilter;
import com.softwaremanager.schedulebuilder.Security.Filter.ExceptionHandlerFilter;
import com.softwaremanager.schedulebuilder.Security.Filter.JWTAuthorizationFilter;
import com.softwaremanager.schedulebuilder.Security.Manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration

@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager authManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter filter = new AuthenticationFilter(authManager);
        filter.setFilterProcessesUrl("/authenticate");

        http
                .csrf(csrf -> {
                    try {
                        csrf
                                .disable()
                                .authorizeHttpRequests((requests) -> requests

                                        .requestMatchers("v1/employee/*/users/create").permitAll()
                                        .anyRequest().authenticated())
                                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                                .addFilter(filter)
                                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                                .sessionManagement(management -> management
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return http.build();
    }

}
