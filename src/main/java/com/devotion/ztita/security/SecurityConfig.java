package com.devotion.ztita.security;

import com.devotion.ztita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())// cors activamos
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/index").hasAnyRole("USER", "ADMIN","ZTITAN")
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("imagenes/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/users/**").hasAnyRole("ADMIN","ZTITAN")//para 2 roles o más
						.requestMatchers("/categoria/**").hasAnyRole("ADMIN","ZTITAN")
						.requestMatchers("/producto/**").hasAnyRole("ADMIN","ZTITAN")
						.requestMatchers("/role/**").hasAnyRole("ADMIN","ZTITAN")
						.requestMatchers("/usuario/**").hasAnyRole("ADMIN","ZTITAN")
						.requestMatchers("/carrito/**").hasAnyRole("ADMIN","ZTITAN")
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.requestMatchers("/imagenes/**").permitAll()
						.requestMatchers(HttpMethod.GET,"/imagenes/**").permitAll()

						.requestMatchers(
								"/swagger-ui/**",
								"/swagger-ui.html",
								"/v3/api-docs",
								"/v3/api-docs/**",
								"/v3/api-docs.yaml",
								"/swagger-resources/**",
								"/configuration/**",
								"/webjars/**"
						).permitAll()
						// Métodos OPTIONS
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						
						.anyRequest().authenticated()
				)
				.formLogin(form -> form.disable())
				.httpBasic(basic -> basic.disable())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // para los password
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioService); // ✅ Le pasamos nuestro servicio
		authProvider.setPasswordEncoder(passwordEncoder()); // ✅ Le pasamos el encoder
		return authProvider;
	}

	/*
	la buena practica es habilitar lo cors pero igual no va a dejar de funcionar pero si tambien lo
	abilitamos va a funcionar porque la parte de los controller ya no vamos a poner cors directamente en
	la parte de los especificos por ejemplo el de login o el de categoria o roles, vamo ztitan*/
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200")); // angular
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
