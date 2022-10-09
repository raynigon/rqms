package com.raynigon.rqms.api.general.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableConfigurationProperties(OidcProperties::class)
class HttpSecurityConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/**").permitAll();
        /*http.oauth2ResourceServer()
                .jwt()
                .decoder(jwtDecoder())
                .jwtAuthenticationConverter(authorityService)*/
        return http.build();
    }

    /*@Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder decoder = ((NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(properties.issuerUri));
        AudienceValidator audienceValidator = AudienceValidator(properties.audience);
        JwtValidator withIssuer = JwtValidators.createDefaultWithIssuer(properties.issuerUri);
        TokenValidator withAudience = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator, authorityService);
        decoder.setJwtValidator(withAudience)
        return decoder;
    }*/
}
