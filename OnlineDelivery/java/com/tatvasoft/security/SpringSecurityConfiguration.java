package com.tatvasoft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter 
{
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	
	@Autowired
  	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	 private static final String[] AUTH_WHITELIST = {
			 			"/login/createNewUser","/login/verifyOnlineLogin"
//			             "/login/loginPage","/rksk/error","/rksk/login/loginPage","/",		 
//			             "/swagger-resources/**",			 
//			             "/signup",
//			             "/webjars/**"		 
			     };
	
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	 }
	
	@Bean
    public CustomJwtAuthenticationFilter authenticationTokenFilterBean() throws Exception 
    {
       return new CustomJwtAuthenticationFilter();
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception 
	{
		return super.authenticationManagerBean();
	}
	
	
	
	@Bean
	public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception 
	{
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
		.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
		//.antMatchers(HttpMethod.POST,"/auth/authenticate").permitAll()
		.anyRequest().authenticated() //.failureUrl("/rksk/timeOutPage")
		.and().formLogin().loginPage("/login/loginPage").defaultSuccessUrl("/rksk/homepage", true)
		.failureUrl("/rksk/error").and().logout().logoutUrl("/login/logout")
		.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().
		  sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(-1);
		return multipartResolver;
	}
}
