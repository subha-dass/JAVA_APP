package com.example.sprngsecurityproject.config;

import com.example.sprngsecurityproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().and().
                csrf().disable().
                authorizeRequests()
                .antMatchers(HttpMethod.GET, "/student/**").hasAuthority(Constants.STUDENT_SELF_INFO_AUTHORITY)
                .antMatchers( "/Admin/**").hasAuthority(Constants.CREATE_BOOK_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

}
