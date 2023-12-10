package org.example.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collections;

@Component
public class JwtValidationFilter extends OncePerRequestFilter implements Ordered {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Auth are"+authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " from the Authorization header

            Claims claims = validateToken(token);
            System.out.println("Claims is"+claims);
            if (claims != null) {
                // If the token is valid, store the authenticated user information in the request attributes
                Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                request.setAttribute("userClaims", claims);
            } else {
                // Token validation failed, you may choose to return an error response or handle it accordingly
                return;
            }
        }

        // Continue with the request processing
        filterChain.doFilter(request, response);
    }

    private Claims validateToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            // Token validation failed
            return null;
        }
    }
    @Override
    public int getOrder() {
        return 1; // Set the desired order value here
    }


}
