package com.github.yangricardo.api_spring_boot.shared.configurations.security;

import com.github.yangricardo.api_spring_boot.modules.auth.services.AuthenticationService;
import com.github.yangricardo.api_spring_boot.modules.auth.services.TokenAuthenticationFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
  prePostEnabled = true,
  securedEnabled = true,
  jsr250Enabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationService authenticationService;

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Autowired
  TokenAuthenticationFilterService tokenAuthenticationFilterService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Configurations for authentication
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(this.passwordEncoder());
  }

  // Configuration for authorization
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
        .csrf()
          .disable()
          .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .antMatchers("/values/**").permitAll()
            .antMatchers("/http-request-sample/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .anyRequest().authenticated()
      .and()
        .addFilterBefore(
          this.tokenAuthenticationFilterService,
          UsernamePasswordAuthenticationFilter.class
        );
  }

  // Configuration for static resources
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers( "/v3/api-docs",
                      "/configuration/ui",
                      "/swagger-resources/**",
                      "/configuration/security",
                      "/swagger-ui.html",
                      "/webjars/**"
        );
  }
}
