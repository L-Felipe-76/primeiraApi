package com.example.AccountsBS.config;

//Spring Framework
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//AccountsBS
import com.example.AccountsBS.security.JWTAuthenticationFilter;

//Dependencies
import lombok.RequiredArgsConstructor;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                            "/api/auth/**",
                            "/bairroSeguro/accounts/**",
                            "/bairroSeguro/moradores/**",
                            "/bairroSeguro/ocorrencias/**",
                            "/bairroSeguro/redesDeApoio/**",
                            "/bairroSeguro/contatosDeEmergencia/**"
                        ).permitAll()
                        .requestMatchers(
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui/index.html",
                            "/swagger-resources/**",
                            "/webjars/**"
                        ).permitAll()
//                        .requestMatchers(
//                            "/bairroSeguro/accounts/**",
//                            "/bairroSeguro/moradores/**",
//                            "/bairroSeguro/ocorrencias/**",
//                            "/bairroSeguro/redesDeApoio/**",
//                            "/bairroSeguro/contatosDeEmergencia/**"
//                        ).authenticated()
                        .anyRequest().authenticated()
                )

                .authenticationProvider(authenticationProvider)

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
