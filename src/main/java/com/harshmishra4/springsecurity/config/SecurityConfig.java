package com.harshmishra4.springsecurity.config;

import com.harshmishra4.springsecurity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Harsh
 * @created 19/03/2025 - 7:05 PM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(request -> {
//            Allowed endpoints - 02
            request.requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                    .anyRequest().authenticated(); // All incoming user's http request must be authenticated
//            request.requestMatchers("/api/v1/**").permitAll();

        })
//                .formLogin(Customizer.withDefaults()); // Form login page to authenticate user
        // Show default login page if user is not authenticated [POP-UP]
                .httpBasic(Customizer.withDefaults());

//        http.csrf(Customizer.withDefaults()); //  To enable CSRF
        http.csrf(AbstractHttpConfigurer::disable); // [Same as] -> http.csrf(csrf -> csrf.disable());
        return http.build();
    }

//    https://www.youtube.com/watch?v=oQV2WGin0mc [ 02:10:07 ]

//    InMemory UserDetails Manager but have limited user base
//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("Harsh")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("SEC_ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
