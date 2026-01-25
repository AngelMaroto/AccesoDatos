package com.angel.apivuelos;

import com.angel.apivuelos.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class ApiVuelosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiVuelosApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final String[] AUTH_WHITELIST = {
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**"
        };

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    // Swagger y Login -> Públicos
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers(HttpMethod.POST, "/user/login").permitAll()

                    // Endpoints Examen: Búsquedas -> Públicas
                    .antMatchers(HttpMethod.GET, "/vuelos/**").permitAll()

                    // Endpoints Examen: Gestión Compañías -> Privadas
                    .antMatchers("/companias/**").authenticated()

                    .anyRequest().authenticated();
        }
    }
}


