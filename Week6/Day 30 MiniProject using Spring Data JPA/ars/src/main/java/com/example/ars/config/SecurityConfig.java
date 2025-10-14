package com.example.ars.config;

import com.example.ars.filter.JwtAuthFilter;
import com.example.ars.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	private final UserInfoService userInfoService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserInfoService userInfoService, PasswordEncoder passwordEncoder) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.userInfoService = userInfoService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions().sameOrigin()) // Enable H2 console frames
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/welcome", "/auth/register", "/auth/login","/actuator","/actuator/**").permitAll()
						.requestMatchers("/auth/users/**","/auth/users").hasAuthority("ROLE_USER")
						.requestMatchers("/auth/users/**","/auth/users").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/auth/admin/**").hasAuthority("ROLE_ADMIN")
						// Admin-only access to H2 console and Swagger UI
						.requestMatchers("/h2-console/**").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").hasAuthority("ROLE_ADMIN")
						.anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userInfoService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
