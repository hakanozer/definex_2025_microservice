package com.works.configs;

import com.works.services.CustomerService;
import com.works.services.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityFilter securityFilter;

    // Security Config
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(req -> req
                        .requestMatchers("/customer/**").permitAll() // login / register
                        .requestMatchers("/product/**").hasRole("product")
                        .requestMatchers("/note/**").hasRole("note")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form.disable())
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                (req, res, authEx) -> {
                                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                                    res.getWriter().write("Unauthorized");
                                }

                        )
                        .accessDeniedHandler(
                                (req, res, deniedEx) ->
                                {
                                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                                    res.getWriter().write("Forbidden");
                                }
                        )
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


}

/*
ali@mail.com -> product
veli@mail.com -> note
zehra@mail.com -> product, note
 */
