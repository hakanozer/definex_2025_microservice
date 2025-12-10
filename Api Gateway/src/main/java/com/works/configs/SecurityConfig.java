package com.works.configs;

import com.works.services.CustomerService;
import com.works.services.JWTService;
import io.micrometer.tracing.Tracer;
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
    private final JWTService jwtService;
    private final Tracer tracer;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                // Stateless güvenlik (JWT için zorunlu)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Yetkilendirme kuralları
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/customer/**").permitAll()  // login/register
                        .requestMatchers("/product/**").hasRole("product")
                        .requestMatchers("/note/**").hasRole("note")
                        .anyRequest().authenticated()
                )

                .formLogin(login -> login.disable())
                .logout(logout -> logout.disable())

                .authenticationProvider(authenticationProvider())

                // Hata yönetimi (en kritik kısım)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) -> {
                            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            res.getWriter().write("Unauthorized");
                        })
                        .accessDeniedHandler((req, res, e) -> {
                            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            res.getWriter().write("Forbidden");
                        })
                )

                // JWT Filter ekle
                .addFilterBefore(
                        new SecurityFilter(jwtService, customerService, tracer),
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
