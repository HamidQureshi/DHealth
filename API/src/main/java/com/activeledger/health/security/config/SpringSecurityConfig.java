package com.activeledger.health.security.config;

import java.util.Arrays;
import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.activeledger.health.auth.JwtAuthenticationProvider;
import com.activeledger.health.auth.JwtAuthenticationTokenFilter;
import com.activeledger.health.auth.JwtFailureHandler;
import com.activeledger.health.auth.JwtSuccessHandler;
import com.activeledger.health.auth.UserAuthenticationFilter;
import com.activeledger.health.service.ActiveService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
/*@EnableWebMvc*/

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ActiveService activeService;
	@Autowired
	private DataSource dataSource;
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${token.secret}")
	private String secret;
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;

	@Bean
	public AuthenticationManager authManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}

	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		filter.setAuthenticationManager(authManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		filter.setAuthenticationFailureHandler(new JwtFailureHandler());
		return filter;
	}

	@Override
	public void configure(WebSecurity web) {
		// Overridden to exclude some url's
		web.ignoring().antMatchers("/css/**","/lib/**","/images/**","/fonts/**","/lang/**","/api", "/api/register", "/api/h2/**","/index.html**", "/api/resources/**", "/resources/**","/static/**","/api/swagger.json","/swagger.json","/webapp/**","/v2/api-docs",
                "/api/v2/api-docs", "/api/configuration/ui", "/api/swagger-resources/**", "/api/configuration/**","/swagger-ui.js", "/api/swagger-ui.html", "/api/webjars/**");
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable()

        
		.authorizeRequests().antMatchers("/api").permitAll().antMatchers("/api/user").permitAll().antMatchers("/api/register", "/resources/**")
				.permitAll().antMatchers("/api/h2/**").permitAll().antMatchers("/api/customLogout/**").permitAll().antMatchers("/index.html**").permitAll()
				 .anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout()
				.logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true)
				.logoutSuccessUrl("/customLogout");
		//http.addFilter( new Authorizationf( authenticationManager()));
		http.addFilter(new UserAuthenticationFilter(authenticationManager(), activeService, secret));
		http.addFilterAfter(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
		http.headers().frameOptions().disable();
		http.cors().configurationSource(corsConfigurationSource());

	}

	
	@Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Token"));
       
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication()

		.usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(usersQuery).dataSource(dataSource)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
