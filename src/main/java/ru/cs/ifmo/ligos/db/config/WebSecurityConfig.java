package ru.cs.ifmo.ligos.db.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOAuth2Client
@EnableAutoConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final OAuth2ClientContext oauth2ClientContext;

	@Autowired
	public WebSecurityConfig(OAuth2ClientContext oauth2ClientContext) {
		this.oauth2ClientContext = oauth2ClientContext;
	}

	@Bean
	@ConfigurationProperties("security.oauth2.google.client")
	public AuthorizationCodeResourceDetails google() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("security.oauth2.google.resource")
	public ResourceServerProperties googleResource() {
		return new ResourceServerProperties();
	}

	private Filter ssoGoogleFilter() {
		OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
		OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oauth2ClientContext);
		googleFilter.setRestTemplate(googleTemplate);
		googleFilter.setTokenServices(new UserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId()));
		return googleFilter;
	}

	@Bean
	@ConfigurationProperties("security.oauth2.instagram.client")
	public AuthorizationCodeResourceDetails instagram() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("security.oauth2.instagram.resource")
	public ResourceServerProperties instagramResource() {
		return new ResourceServerProperties();
	}

	private Filter ssoInstagramFilter() {
		OAuth2ClientAuthenticationProcessingFilter instagramFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/instagram");
		OAuth2RestTemplate instagramTemplate = new OAuth2RestTemplate(instagram(), oauth2ClientContext);
		instagramFilter.setRestTemplate(instagramTemplate);
		instagramFilter.setTokenServices(new UserInfoTokenServices(instagramResource().getUserInfoUri(), instagram().getClientId()));
		return instagramFilter;
	}

	private Filter ssoFilter() {
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoGoogleFilter());
		filters.add(ssoInstagramFilter());

		CompositeFilter filter = new CompositeFilter();
		filter.setFilters(filters);
		return filter;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.antMatcher("/**")
					.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/chooseLogin")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
					.formLogin()
						.loginPage("/chooseLogin");
	}
	/*@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.mvcMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
	}*/

	/*@Bean
	public PrincipalExtractor principalExtractor(UserService userService){
		return map -> {
			String email = (String)map.get("email");

			UserEntity user = userService.getUserByEmail(email);

			if (user == null){

				UserEntity newUser = new UserEntity();
				newUser.setName((String) map.get("given_name"));
				newUser.setPassword("1231");
				newUser.setEmail((String) email);
				newUser.setPhoto((String) map.get("picture"));

				userService.save(newUser);

				return newUser;
			}

			return user;
		};
	}*/
}

//given_name
//email
//picture