package com.epam.springtraining.security;

import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().
                loginPage("/login").
                and().
                    authorizeRequests().
                        antMatchers(HttpMethod.POST, "/get_price").hasRole("REGISTERED_USER").
                        antMatchers("/tickets").hasRole("BOOKING_MANAGER").
                        antMatchers(HttpMethod.POST, "/book_ticket").hasRole("BOOKING_MANAGER").
                        anyRequest().permitAll().
                and().
                    rememberMe().
                        tokenValiditySeconds(2419200).
                        key("spittrKey").
                and().
                    logout().
                        logoutSuccessUrl("/").
                and().
                    csrf().
                        disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//            .usersByUsernameQuery("SELECT EMAIL, PASSWORD, TRUE FROM USER WHERE EMAIL=?")
//            .authoritiesByUsernameQuery("SELECT EMAIL, ROLE FROM USER WHERE EMAIL=?")
//            .passwordEncoder(new StandardPasswordEncoder("springTraining"));
        auth.userDetailsService(new SpringTrainingUserService(userService)).
                passwordEncoder(new StandardPasswordEncoder("springTraining"));
    }


}
