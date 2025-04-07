package com.example.tms.filter;

import com.example.tms.services.CustomUserDetailsService;
import com.example.tms.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Get Authorization header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // 2. Check if Bearer token exists
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);

            // 3. Extract username from JWT
            try{
                username = jwtUtil.extractUsername(token);
            }catch (Exception e){
                System.out.println("Invalid JWT token");
            }
        }

        // 4. If username is not null and no authentication is set yet
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            // 5. Load user details
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            // 6. Check if token is valid
            if (jwtUtil.validateToken(token, userDetails)){

                // 7. Create Authentication token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 8. Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9. Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}
