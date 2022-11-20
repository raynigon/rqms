package com.raynigon.rqms.api.general.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The properties used in the OIDC Security Configuration
 * */
@ConfigurationProperties(prefix = "rqms.security.oidc")
public record OidcProperties(
        String issuerUri,
        String audience,
        String scope
) {
}