package com.aravindh.gymtracker.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        String uri = request.getRequestURI();
        if ("/login".equals(uri) || "/signup".equals(uri)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                if (jwtService.isValid(token)) {
                    String email = jwtService.extractEmail(token);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            null);

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(auth);

                    filterChain.doFilter(request, response);
                    return;
                } else {
                    response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                    );
                    return;
                }
            } else {
                response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
                );
                return;
            }
        }

    }

}
