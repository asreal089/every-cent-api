package com.ever.cent.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.ever.cent.config.security.jwt.TokenAuthenticationFilter;
import com.ever.cent.config.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ever.cent.config.security.oauth2.OAuth2AccessTokenResponseConverterWithDefaults;
import com.ever.cent.config.security.oauth2.user.CustomOAuth2UserService;
import com.ever.cent.config.security.oauth2.user.CustomOidcUserService;
import com.ever.cent.config.security.oauth2.user.OAuth2AuthenticationFailureHandler;
import com.ever.cent.config.security.oauth2.user.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	CustomOidcUserService customOidcUserService;

	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(10));
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
			.disable().formLogin().disable().httpBasic().disable().exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
		http.authorizeHttpRequests()
		.requestMatchers("/api-docs").permitAll()
		.requestMatchers("/api-docs/swagger-config").permitAll()
		.requestMatchers("/v3/api-docs").permitAll()
		.requestMatchers("/configuration/ui").permitAll()
		.requestMatchers("/swagger-resources/**").permitAll()
		.requestMatchers("/configuration/security").permitAll()
		.requestMatchers("/swagger-ui.html").permitAll()
		.requestMatchers("/webjars/**").permitAll()
		.requestMatchers("/swagger-ui/**").permitAll()
		.requestMatchers("**/favicon.ico/**").permitAll()
		.requestMatchers("swagger-ui.html").permitAll()
		.requestMatchers("/v3/api-docs/swagger-config").permitAll()
		.requestMatchers("/", "/error", "/api/all", "/api/auth/**").permitAll()
		.requestMatchers("/oauth2/**").permitAll()
		.requestMatchers("/tipo-lancamento").permitAll()
		.anyRequest().authenticated().and().oauth2Login().authorizationEndpoint()
		.authorizationRequestRepository(cookieAuthorizationRequestRepository()).and().redirectionEndpoint()
		.and().userInfoEndpoint().oidcUserService(customOidcUserService).userService(customOAuth2UserService)
		.and().tokenEndpoint().accessTokenResponseClient(authorizationCodeTokenResponseClient()).and()
		.successHandler(oAuth2AuthenticationSuccessHandler).failureHandler(oAuth2AuthenticationFailureHandler);
		// Add our custom Token based authentication filter
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	public AuthenticationManager authenticationManager(List<AuthenticationProvider> providers) {
		return new ProviderManager(providers);
	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
		OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
		tokenResponseHttpMessageConverter
				.setAccessTokenResponseConverter(new OAuth2AccessTokenResponseConverterWithDefaults());
		RestTemplate restTemplate = new RestTemplate(
				Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
		DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
		tokenResponseClient.setRestOperations(restTemplate);
		return tokenResponseClient;
	}
}
