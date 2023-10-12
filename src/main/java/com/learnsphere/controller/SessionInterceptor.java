package com.learnsphere.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.learnsphere.session.Session;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Check if a session exists
    	 System.out.println("Working in Pre Handler");
        if (Session.check()) {
        	 System.out.println("Working in PH !");
            return true; // Continue with the request
        } else {
            // Redirect to the login screen
            response.sendRedirect("/login"); // Adjust the URL to your login page
            return false; // Stop further processing
        }
    }
}
