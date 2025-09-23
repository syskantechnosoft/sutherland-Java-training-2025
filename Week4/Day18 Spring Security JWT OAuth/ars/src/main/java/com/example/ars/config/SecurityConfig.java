package com.example.ars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()) // Disable CSRF for H2-Console
//		).headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()) // Disable X-Frame-Options
//		).authorizeHttpRequests(authorize -> authorize.requestMatchers(PathRequest.toH2Console()).permitAll()
//				.requestMatchers("/h2-ui/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll() 
//				// Allow All requests  to  H2-Console &  Swagger
//				.anyRequest().authenticated() // Secure other requests
//		);
//		return http.build();
//	}

//	private static final String[] AUTH_WHITELIST = {
//			// for Swagger UI v2
//			"/v2/api-docs", "/swagger-ui.html", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
//			"/configuration/security", "/webjars/**",
//			// for Swagger UI v3 (OpenAPI)
//			"/v3/api-docs/**", "/swagger-ui/**", "/api/v1/login", "/api/v1/logout", "/swagger-ui/**",
//			"/api/v1/v3/api-docs", "/swagger-ui.html","/api/v1/h2-console","/api/v1/h2-console/**" };
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()) // Disable CSRF for H2-Console
//		).headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()) // Disable X-Frame-Options
//		).authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll()
//				.requestMatchers(PathRequest.toH2Console()).permitAll()
//				// Allow all requests to H2-Console
//				.anyRequest().authenticated() // Secure other requests
//		);
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**")
				.authenticated()
						// Swagger H2 Also Require Login
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()) // Basic auth
				.formLogin(Customizer.withDefaults()) // Form login also works
				.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Allow H2 console
				.headers(headers -> headers.frameOptions().sameOrigin()); // For H2 console frames

		return http.build();
	}
}
