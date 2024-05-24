package awa.training.api.core.security.authorization;

import awa.training.api.core.configs.ApplicationConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	private final AuthenticationConfiguration configuration;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configCors = new CorsConfiguration();
		configCors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		configCors.setAllowedOrigins(this.applicationConfig.getCors().getAllowedOrigins());
		configCors.setAllowedMethods(this.applicationConfig.getCors().getAllowedMethods());
		configCors.setAllowedHeaders(this.applicationConfig.getCors().getAllowedHeaders());
		configCors.setAllowCredentials(this.applicationConfig.getCors().getAllowCredentials());
		configCors.setMaxAge(this.applicationConfig.getCors().getMaxAge());
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configCors);
		return source;
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Order(1)
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								antMatcher("/authenticate/login"),
								antMatcher("/authenticate/version"),
								antMatcher("/api/users/register"),
								antMatcher("/image/get-images")
								)
						.permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(eh -> eh.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}

}
