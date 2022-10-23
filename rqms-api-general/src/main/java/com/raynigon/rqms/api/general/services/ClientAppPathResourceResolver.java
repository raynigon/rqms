package com.raynigon.rqms.api.general.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

public class ClientAppPathResourceResolver extends PathResourceResolver {

    @Override
    public Resource getResource(@NonNull String resourcePath, Resource location) throws IOException {
        Resource requestedResource = location.createRelative(resourcePath);
        if (requestedResource.exists() && requestedResource.isReadable()) {
            return requestedResource;
        } else if ((location instanceof ClassPathResource) && ((ClassPathResource) location).getPath().equals("static/")) {
            return new ClassPathResource("/static/index.html");
        }
        return null;
    }
}
