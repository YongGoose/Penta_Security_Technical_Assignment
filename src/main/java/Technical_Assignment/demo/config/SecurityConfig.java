package Technical_Assignment.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String[] PUBLIC_URLS = {
		"/auth/signin",
		"/auth/signup",
		"/css/**",
		"/js/**",
		"/images/**"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.antMatchers(PUBLIC_URLS).permitAll()
				// Rest API 허용
				.antMatchers(HttpMethod.POST, "/user/create").permitAll()
				.antMatchers(HttpMethod.PUT, "/user/update").permitAll()
				.antMatchers(HttpMethod.DELETE, "/user/delete").permitAll()
				.antMatchers(HttpMethod.GET, "/user/find/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin().disable()
			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/auth/signout"))
				.logoutSuccessUrl("/auth/signin")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
			)
			.sessionManagement(session -> session
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true)
			)
			.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}