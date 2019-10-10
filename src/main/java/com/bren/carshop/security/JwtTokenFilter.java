package com.bren.carshop.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenTool jwtTokenTool;

    public JwtTokenFilter(JwtTokenTool jwtTokenTool) {
        this.jwtTokenTool = jwtTokenTool;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = jwtTokenTool.getTokenByBody((HttpServletRequest) req);
        if (token != null && jwtTokenTool.isTokenValid(token)) {
            Authentication auth = jwtTokenTool.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }
}
