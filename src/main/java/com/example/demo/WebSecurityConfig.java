package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/home", "/registro", "/error").permitAll()
                        .requestMatchers("/style.css", "/css/**", "/js/**", "/img/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/eventos", true)
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails gamer1 = User.builder()
                .username("gamer1@gamer.cl")
                .password(passwordEncoder().encode("clave12345"))
                .roles("USER")
                .build();
        UserDetails gamer2 = User.builder()
                .username("gamer2@gamer.cl")
                .password(passwordEncoder().encode("clave12345"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin@gamer.cl")
                .password(passwordEncoder().encode("admin12345"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(gamer1, gamer2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}