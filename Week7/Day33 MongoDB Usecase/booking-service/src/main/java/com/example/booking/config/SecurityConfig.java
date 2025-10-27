package com.example.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.booking.filter.JwtFilter;
import com.example.booking.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private JwtUtil jwtUtil;

	public SecurityConfig(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/actuator/**", "/health").permitAll()
						// Swagger restricted to ADMIN role
						.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
				// Redirect unauthorized to /auth/login
//				.exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> {
//					response.setStatus(HttpServletResponse.SC_FOUND);
//					response.setHeader("Location", "/auth/login");
//				}))
				;

		return http.build();
	}

}
