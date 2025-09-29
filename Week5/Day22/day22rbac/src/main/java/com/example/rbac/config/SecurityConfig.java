package com.example.rbac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.rbac.filter.JwtAuthFilter;
import com.example.rbac.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	private final UserInfoService userInfoService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserInfoService userInfoService,
			PasswordEncoder passwordEncoder) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.userInfoService = userInfoService;
		this.passwordEncoder = passwordEncoder;
	}

	/*
	 * Main security configuration Defines endpoint access rules and JWT filter
	 * setup
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Disable CSRF (not needed for stateless JWT)
				.csrf(csrf -> csrf.disable())

				// Configure endpoint authorization
				.authorizeHttpRequests(auth -> auth
						// Public endpoints
						.requestMatchers("/auth/welcome", "/auth/signup", "/auth/signin", "/swagger-ui/**","/v3/api-docs/**",
				                "/swagger-ui/**",
				                "/swagger-ui.html",
				                "/swagger-resources/**",
				                "/webjars/**").permitAll()
						
						// Role-based endpoints
						.requestMatchers("/auth/user/**").hasAuthority("ROLE_USER").requestMatchers("/auth/admin/**")
						.hasAuthority("ROLE_ADMIN")

						// All other endpoints require authentication
						.anyRequest().authenticated())

				// Stateless session (required for JWT)
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Set custom authentication provider
				.authenticationProvider(authenticationProvider())

				// Add JWT filter before Spring Security's default filter
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * Authentication provider configuration Links UserDetailsService and
	 * PasswordEncoder
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userInfoService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	/*
	 * Authentication manager bean Required for programmatic authentication (e.g.,
	 * in /signin)
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
