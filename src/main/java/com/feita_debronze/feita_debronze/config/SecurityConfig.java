package com.feita_debronze.feita_debronze.config;

import com.feita_debronze.feita_debronze.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/agendamentos/pre-reserva").permitAll()
                .requestMatchers("/api/agendamentos/pre-reserva/**").permitAll()

                // Endpoints públicos ou específicos
                .requestMatchers("/api/agendamentos/**").hasAnyRole("ADMIN", "RESPONSAVEL")
                .requestMatchers("/api/compras/**").hasAnyRole("ADMIN", "RESPONSAVEL")
                .requestMatchers("/api/contas/**").hasAnyRole("ADMIN", "RESPONSAVEL")

                .requestMatchers("/api/produtos/**").hasAnyRole("ADMIN", "RESPONSAVEL")
                // 1. Libera CONSULTA (GET) pública para Unidades e Procedimentos
                .requestMatchers(HttpMethod.GET, "/api/unidades/**", "/api/procedimentos/**").permitAll()

                .requestMatchers("/api/procedimentos/**").hasAnyRole("ADMIN", "RESPONSAVEL")
                .requestMatchers("/api/unidades/**").hasAnyRole("ADMIN", "RESPONSAVEL")

                .requestMatchers("/api/usuarios/**").hasRole("ADMIN") // Apenas ADMIN pode gerenciar usuários
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/responsavel/**").hasAnyRole("RESPONSAVEL", "ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // Permite todas as origens
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*")); // Permite todos os headers
        config.setAllowCredentials(false); // Não pode ser true com allowedOrigins("*")

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
