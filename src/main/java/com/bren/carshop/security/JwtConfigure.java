package com.bren.carshop.security;


import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenTool jwtTokenTool;

    public JwtConfigure(JwtTokenTool jwtTokenTool) {
        this.jwtTokenTool = jwtTokenTool;
    }

    @Override
    public void configure(HttpSecurity httpSecurity){
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenTool);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
