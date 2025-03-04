package com.sachin.security;


import io.jsonwebtoken.Claims;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sachin.dto.other.HeaderHolder;

import java.io.IOException;

/**
 * Author : SachinSilva
 */
@RequestScoped
@WebFilter("/*")
public class JwtFilter implements Filter {

    @Inject
    private HeaderHolder headerHolder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        try {
            Claims claims = JwtUtil.validateToken(token);
            req.setAttribute("user", claims.getSubject());
            req.setAttribute("role", claims.get("role"));
            chain.doFilter(request, response);
        } catch (Exception e) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

