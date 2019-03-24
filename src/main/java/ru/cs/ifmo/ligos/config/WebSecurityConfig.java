package ru.cs.ifmo.ligos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import ru.cs.ifmo.ligos.security.jwt.JwtAuthenticationEntryPoint;
import ru.cs.ifmo.ligos.security.jwt.JwtAuthenticationFilter;
import ru.cs.ifmo.ligos.security.MyUserDetailsService;
import ru.cs.ifmo.ligos.security.oauth2.CustomOAuth2UserService;
import ru.cs.ifmo.ligos.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import ru.cs.ifmo.ligos.security.oauth2.OAuth2AuthenticationFailureHandler;
import ru.cs.ifmo.ligos.security.oauth2.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final MyUserDetailsService myUserDetailsService;
	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;



	@Autowired
	public WebSecurityConfig(MyUserDetailsService myUserDetailsService,
							 JwtAuthenticationEntryPoint unauthorizedHandler,
							 CustomOAuth2UserService customOAuth2UserService,
							 OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
							 OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler,
							 HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
		this.myUserDetailsService = myUserDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.customOAuth2UserService = customOAuth2UserService;
		this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
		this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
		this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
	}

	@Bean
	public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver(){
		return new AuthenticationPrincipalArgumentResolver();
	}

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(myUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.csrf()
					.disable()
				.exceptionHandling()
					.authenticationEntryPoint(unauthorizedHandler)
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
				.antMatchers("/",
						"/favicon.ico",
						"/**/*.png",
						"/**/*.gif",
						"/**/*.svg",
						"/**/*.jpg",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js")
					.permitAll()
				.antMatchers("/users/*", "/section/**")
					.permitAll()
				.antMatchers("/organizations/*")
					.permitAll()
				.antMatchers(HttpMethod.GET, "/organizations", "/users")
					.permitAll()
				.antMatchers("/auth/**", "/oauth2/**")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
				.oauth2Login()
					.authorizationEndpoint()
						.baseUri("/oauth2/authorize")
						.authorizationRequestRepository(cookieAuthorizationRequestRepository())
						.and()
					.redirectionEndpoint()
						.baseUri("/oauth2/callback/*")
					.and()
						.userInfoEndpoint()
							.userService(customOAuth2UserService)
						.and()
							.successHandler(oAuth2AuthenticationSuccessHandler)
							.failureHandler(oAuth2AuthenticationFailureHandler);

		// Add our custom JWT security filter
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}
}