package com.devoir.benatia_maliki;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; 
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import com.devoir.benatia_maliki.services.UserService;

@Configuration
@EnableWebSecurity
public class SecuriteConfig  extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;
	public class DeniedAuthenticationEntryPoint extends Http403ForbiddenEntryPoint{
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException {
			// TODO Auto-generated method stub
			response.sendRedirect("/login.html");
		}
		
	}

	private class UrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			// TODO Auto-generated method stub
		    super.onAuthenticationFailure(request, response, exception);
			response.sendRedirect("/login.html");
		}
	}

	private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);
			
			response.sendRedirect("/layout.html");
		}
	}

	private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("/login.html");
		}
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.mvcMatchers("/id","/index", "/login", "/logout", "/inscription").permitAll()
				.mvcMatchers("projets").hasRole("DEV")
				.mvcMatchers("mvcprojets").hasRole("USER")
				.mvcMatchers("admin").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				 
	      
		 		.and()
				.logout()
				.clearAuthentication(true).invalidateHttpSession(true)
				.and()
				.csrf().disable() .exceptionHandling()
               
             ;
               
	}

	

	} 