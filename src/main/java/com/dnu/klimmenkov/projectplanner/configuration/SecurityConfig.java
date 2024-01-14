package com.dnu.klimmenkov.projectplanner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/about", "/css/**","/images/**" ).permitAll()
                        .requestMatchers("/**").authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .formLogin(login -> login
                        .defaultSuccessUrl("/home")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/about"))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager inMemoryUserDetailsManager() {
        var user1 = User.withUsername("user").password(passwordEncoder().encode("user")).build();
        var user2 = User.withUsername("admin").password(passwordEncoder().encode("admin")).build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
