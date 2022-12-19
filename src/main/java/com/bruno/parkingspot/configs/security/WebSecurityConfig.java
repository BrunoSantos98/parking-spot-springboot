package com.bruno.parkingspot.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http
               .csrf().disable()
               .authorizeHttpRequests().requestMatchers("/register").permitAll()
               .and().authorizeHttpRequests()
               .requestMatchers(HttpMethod.GET,"/cars/**","/parking-spot/**","/dependents/**").authenticated()
               .and().authorizeHttpRequests().requestMatchers("/**").hasAnyRole("ADMIN","DBA")
               .anyRequest().authenticated()
               .and().httpBasic();
       return http.build();
   }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
