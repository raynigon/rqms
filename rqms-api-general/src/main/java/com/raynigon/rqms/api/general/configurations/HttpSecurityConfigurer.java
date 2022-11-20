package com.raynigon.rqms.api.general.configurations;

import com.raynigon.rqms.api.general.model.OidcProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The Web Security Configuration which can be configured to use OIDC
 * Currently no Authentication is needed for the development
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties(OidcProperties.class)
class HttpSecurityConfigurer {

    private final OidcProperties properties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/**").permitAll();

        /*http.oauth2ResourceServer()
                .jwt()
                .decoder(jwtDecoder())
                .jwtAuthenticationConverter(authorityService)*/
        return http.build();
    }

    /*@Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder decoder = ((NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(properties.issuerUri()));
        AudienceValidator audienceValidator = AudienceValidator(properties.audience());
        JwtValidator withIssuer = JwtValidators.createDefaultWithIssuer(properties.issuerUri());
        TokenValidator withAudience = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator, authorityService);
        decoder.setJwtValidator(withAudience)
        return decoder;
    }*/
}
