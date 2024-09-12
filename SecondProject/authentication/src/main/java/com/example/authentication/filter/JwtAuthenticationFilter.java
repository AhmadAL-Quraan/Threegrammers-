package com.example.authentication.filter;

import com.example.authentication.Security.JWTUtility;
import com.example.authentication.Service.UserDetailServiceCustom;  // Assuming you have a service to load user details
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.*;

import java.beans.JavaBean;
import java.io.IOException;
@Component
@JavaBean
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserDetailServiceCustom userDetailService; 
    
    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {        
                String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                // Validate the token
                if (jwtUtility.isTokenExpired(token)) {
                    // Token is expired, no further processing
                    filterChain.doFilter(request, response);
                    return;
                }
                
                // Extract user information from token
                String username = jwtUtility.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Load user details from the service
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    
                    if (userDetails != null) {
                        // Create authentication token
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, null);
                        
                        // Set details 
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((jakarta.servlet.http.HttpServletRequest) request));

                        // Set the authentication in the security context
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                // Handle exceptions and invalid tokens --> securtiy context
                SecurityContextHolder.clearContext();
            
            }
        }

        filterChain.doFilter(request, response);
    }

   

    
}
