package com.carmada.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages="com.carmada")
//public class SecurityConfig {
//
//	// add a reference to our security data source
//	
//	private DataSource securityDataSource;
//
//	@Autowired
//	public SecurityConfig(@Qualifier("securityDataSource")DataSource theSecurityDataSource) {
//		securityDataSource = theSecurityDataSource;
//	}
//
//	@Bean
//	public UserDetailsManager userDetailsManager() {
//		return new JdbcUserDetailsManager(securityDataSource);
//	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http
//			.csrf()
//	        .and()
//			.authorizeRequests(configurer -> 
//				configurer
//					.antMatchers("/*").hasRole("EMPLOYEE")
//					.antMatchers("/drivers/*").hasRole("EMPLOYEE")
//					.antMatchers("/payments/*").hasRole("EMPLOYEE")
//					.antMatchers("/vehicles/*").hasRole("EMPLOYEE")
//					.antMatchers("/accounting/*").hasRole("EMPLOYEE")
//					)
//			
//			.formLogin(configurer -> 
//				configurer
//					.loginPage("/login")
//					.loginProcessingUrl("/authenticateTheUser")
//					.defaultSuccessUrl("/")
//					.permitAll())
//			
//			.logout(configurer -> 
//				configurer
//					.permitAll())
//			
//			.exceptionHandling(configurer -> 
//				configurer
//					.accessDeniedPage("/access-denied"))
//			
//			.build();
//	}
//	
//		
//}

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.carmada")
public class SecurityConfig {

	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf()
	            .and()
	            .authorizeRequests(authz -> authz
					.mvcMatchers("/drivers/**").authenticated()
					.mvcMatchers("/payments/**").authenticated()
					.mvcMatchers("/vehicles/**").authenticated()
					.mvcMatchers("/incidents/**").authenticated()
					.mvcMatchers("/accounting/**").authenticated()
					.mvcMatchers("/*").authenticated()
					)
	            .oauth2Login()
	            	.loginPage("/login")
	            	.defaultSuccessUrl("/home")
	            	.permitAll()
	            .and()
	            .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login");
	        return http.build();
	    }
	 
}


