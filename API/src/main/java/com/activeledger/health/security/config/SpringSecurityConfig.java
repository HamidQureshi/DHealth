package com.activeledger.health.security.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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

import com.activeledger.health.auth.JwtAuthenticationProvider;
import com.activeledger.health.auth.JwtAuthenticationTokenFilter;
import com.activeledger.health.auth.JwtFailureHandler;
import com.activeledger.health.auth.JwtSuccessHandler;
import com.activeledger.health.auth.UserAuthenticationFilter;
import com.activeledger.health.model.Resp;
import com.activeledger.health.service.ActiveService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableWebSecurity

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Autowired
    private ActiveService activeService;
    @Autowired
    private DataSource dataSource;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
	@Value("${token.secret}")
	private String secret;
    ObjectMapper mapper=new ObjectMapper();
    
    
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
       web.ignoring().antMatchers("/register","/h2/**","/resources/**");
    }
 
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf().disable()
        		
                .authorizeRequests()
               .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler((req, rsp, e) -> {
 
                	Resp resp=new Resp();
                	resp.setCode(rsp.getStatus());
                	resp.setDesc("Invalid username or password");
                	rsp.getWriter().write(mapper.writeValueAsString(resp));
                	
                })
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.addFilter(new UserAuthenticationFilter(authenticationManager(),activeService,secret));
       http.addFilterAfter(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }
  
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    	auth.
        jdbcAuthentication()

        .usersByUsernameQuery(usersQuery)
        .authoritiesByUsernameQuery(usersQuery)
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder());
    }
 


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
	

