package com.learnsphere.controller;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/**")
@Component
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the user has a session, and manage sessions as needed
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // Redirect unauthenticated users to the login page
            if (!httpRequest.getRequestURI().endsWith("/login") &&
                !httpRequest.getRequestURI().endsWith("/register")&&!httpRequest.getRequestURI().endsWith("/")&&
                !httpRequest.getRequestURI().endsWith("/aboutme")&& !httpRequest.getRequestURI().endsWith("/verify")) {
                // You can adjust the URL pattern to exclude other paths that don't require authentication
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if any
    }

    @Override
    public void destroy() {
        // Cleanup logic, if any
    }
}
