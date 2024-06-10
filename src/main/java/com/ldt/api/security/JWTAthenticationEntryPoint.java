package com.ldt.api.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper(); // Khởi tạo đối tượng ObjectMapper

        Map<String, Object> res = new HashMap<>();
        res.put("status", false);
        res.put("msg", authException.getMessage());

        // Chuyển đổi mảng sang chuỗi JSON
        String json = mapper.writeValueAsString(res);
        writer.println(json);
    }
}
