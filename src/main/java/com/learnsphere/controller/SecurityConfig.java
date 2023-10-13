//package com.learnsphere.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/adduser").permitAll()
//                .antMatchers("/welcome").permitAll()
//                .antMatchers("/student").hasRole("STUDENT")
//                .antMatchers("/trainer").hasRole("TRAINER")
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/verify")
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder());
//    }
//}
//
