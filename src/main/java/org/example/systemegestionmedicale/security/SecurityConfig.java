package org.example.systemegestionmedicale.security;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/patients/**").hasAnyRole("ADMIN", "MEDECIN", "PATIENT")
                        .requestMatchers(HttpMethod.POST, "/api/medecins/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/medecins/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/medecins/**").hasAnyRole("ADMIN", "MEDECIN", "PATIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/dossiers-medicaux/**").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/dossiers-medicaux/**").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.GET, "/api/dossiers-medicaux/**").hasAnyRole("ADMIN", "MEDECIN", "PATIENT")
                        .requestMatchers("/api/rendez-vous/**").hasAnyRole("ADMIN", "MEDECIN", "PATIENT")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();

    }
}
