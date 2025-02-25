package com.example.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/helloadmin").hasRole("ADMIN")
						.anyRequest().authenticated()  // Yêu cầu đăng nhập cho tất cả request
				)
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout((logout) -> logout.permitAll())
				.exceptionHandling((exception) -> exception
						.accessDeniedPage("/access-denied")  // Trang lỗi 403 tùy chỉnh
				);

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin =
				User.withDefaultPasswordEncoder()
						.username("admin")
						.password("123")
						.roles("ADMIN")
						.build();
		UserDetails user =
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("password")
						.roles("USER")
						.build();
		UserDetails mix =
				User.withDefaultPasswordEncoder()
						.username("mix")
						.password("password")
						.roles("USER", "ADMIN")
						.build();
		return new InMemoryUserDetailsManager(admin, user, mix);
	}
}
