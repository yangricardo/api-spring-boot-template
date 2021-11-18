package br.pucrio.les.esg_token_backend.configurations.infra.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {

    private String realm;
    private String authServerUrl;
    private String resource;
    private String credentialSecret;

    @Autowired
    public KeycloakClientConfig(@Value("${keycloak.realm}") String realm,
            @Value("${keycloak.auth-server-url}") String authServerUrl, @Value("${keycloak.resource}") String resource,
            @Value("${keycloak.credentials.secret}") String credentialSecret) {
        this.realm = realm;
        this.authServerUrl = authServerUrl;
        this.resource = resource;
        this.credentialSecret = credentialSecret;
        System.out.println("KeycloakClientConfig");
        System.out.println(this.authServerUrl);
        System.out.println(this.realm);
        System.out.println(this.resource);
        System.out.println(this.credentialSecret);
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder().grantType(OAuth2Constants.CLIENT_CREDENTIALS).serverUrl(this.authServerUrl)
                .realm(this.realm).clientId(this.resource).clientSecret(this.credentialSecret).build();
    }
}
