package ru.cs.ifmo.ligos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.cs.ifmo.ligos.security.JwtTokenFilterConfigurer;
import ru.cs.ifmo.ligos.security.JwtTokenProvider;
import ru.cs.ifmo.ligos.security.MyUserDetails;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		http.authorizeRequests()//
				.antMatchers("/users/signin").permitAll()//
				.antMatchers("/users/signup").permitAll()//
				// Disallow everything else..
				.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/login");

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

		// Optional, if you want to test the API from a browser
		// http.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow swagger to be accessed without authentication
		web.ignoring().antMatchers("/v2/api-docs")//
				.antMatchers("/swagger-resources/**")//
				.antMatchers("/swagger-ui.html")//
				.antMatchers("/configuration/**")//
				.antMatchers("/webjars/**")//
				.antMatchers("/public");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}