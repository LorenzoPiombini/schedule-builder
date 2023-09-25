package com.softwaremanager.schedulebuilder.Security.Filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaremanager.schedulebuilder.Constant.Constant;
import com.softwaremanager.schedulebuilder.Entity.Users;
import com.softwaremanager.schedulebuilder.Security.Manager.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationManager authManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            Users user = new ObjectMapper().readValue(request.getInputStream(), Users.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword());
            return authManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.TOEKN_TIME_EXIRATION))
                .sign(Algorithm.HMAC512(Constant.SECRET_KEY));

        response.addHeader(Constant.AUTHORIZATION, Constant.BEARER + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();

    }

}
