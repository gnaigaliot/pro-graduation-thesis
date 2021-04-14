package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private AppUserDetailService userDetailService;
//
//	@Bean
//	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
//		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
//		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
//		return jwtAuthenticationTokenFilter;
//	}
//
//	@Bean
//	public RestAuthenticationEntryPoint restServicesEntryPoint() {
//		return new RestAuthenticationEntryPoint();
//	}
//
//	@Bean
//	public CustomAccessDeniedHandler customAccessDeniedHandler() {
//		return new CustomAccessDeniedHandler();
//	}
//
//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		// Disable crsf cho đường dẫn /rest/**
//		http.csrf().ignoringAntMatchers("/rest/**");
//
//		http.authorizeRequests().antMatchers("/rest/login**").permitAll();
//
//		http.antMatcher("/rest/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
////				.antMatchers(HttpMethod.GET, "/rest/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SV')")
////				.antMatchers(HttpMethod.POST, "/rest/**", "/utt-ess/criterion/**").access("hasRole('ROLE_ADMIN')")
////				.antMatchers(HttpMethod.DELETE, "/rest/**").access("hasRole('ROLE_ADMIN')")
//				.and()
//				.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		
		 http.csrf().disable().authorizeRequests()
//          No need authentication.
         .antMatchers("/").permitAll() //
         .antMatchers(HttpMethod.POST, "/rest/login").permitAll() //
//          Need authentication.
         .anyRequest().authenticated()
         
         .and()
         
//          Add Filter 1 - JWTLoginFilter
         
         .addFilterBefore(new JWTLoginFilter("/rest/login", authenticationManager()),
                 UsernamePasswordAuthenticationFilter.class)
         
//          Add Filter 2 - JWTAuthenticationFilter
         
         .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 
//        String password = "1";
// 
//        String encrytedPassword = this.passwordEncoder().encode(password);
//        System.out.println("Encoded password of 123=" + encrytedPassword);
// 
//        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
//        mngConfig = auth.inMemoryAuthentication();
// 
//        // Defines 2 users, stored in memory.
//        // ** Spring BOOT >= 2.x (Spring Security 5.x)
//        // Spring auto add ROLE_
//        UserDetails u1 = User.withUsername("AT110400").password(encrytedPassword).roles("USER").build();
//        UserDetails u2 = User.withUsername("jerry").password(encrytedPassword).roles("USER").build();
// 
//        mngConfig.withUser(u1);
//        mngConfig.withUser(u2);
// 
//        // If Spring BOOT < 2.x (Spring Security 4.x)):
//        // Spring auto add ROLE_
//        mngConfig.withUser("AT110400").password("1").roles("USER");
//        mngConfig.withUser("jerry").password("123").roles("USER");
// 
//    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Create a default account
        
        auth.userDetailsService(userDetailService);
        
//        auth.inMemoryAuthentication()
//                .withUser("AT110400")
//                .password(this.passwordEncoder().encode("1"))
//                .roles("ADMIN");
    }
}
