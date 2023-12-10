package org.example.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.authenticated;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtValidationFilter jwtValidationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and()
//                .cors()
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                // Configure your URL access rules here
//                .antMatchers("/cart/**").authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .cors()
                .and()
                .csrf().disable() // Disable CSRF protection for simplicity, but you should enable it in a production environment
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // We don't need sessions as we're using JWT
                .and()
                .authorizeRequests()
                .antMatchers("/cart/**").authenticated()// Secure the "/cart/**" URLs, requires authentication with a valid JWT token// Allow all other URLs to be accessed without authentication
                .and()
                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Allow requests from any origin (You can set specific origins here)
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        configuration.setAllowCredentials(true); // Allow sending and receiving cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/cart/**", configuration);
        return source;
    }
}
