package com.Spotivent.SpotiventBackend.auth.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class Claims {
    public static Map<String, Object> getClaims() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication auth = securityContext.getAuthentication();
    Jwt jwt = (Jwt) auth.getPrincipal();
    return jwt.getClaims();

    }
}
