package br.pucrio.les.esg_token_backend.configurations.infra.keycloak;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter = new KeycloakJwtAuthenticationConverter();

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests(authz -> authz.antMatchers("/keycloak/**").hasAnyAuthority("ROLE_USER"))
                .authorizeRequests(authz -> authz.antMatchers("/security-test/*").authenticated())
                .authorizeRequests(authz -> authz.antMatchers("/values/**").permitAll())
                .authorizeRequests(authz -> authz.antMatchers(HttpMethod.POST, "/signup").permitAll())
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);
    }
}