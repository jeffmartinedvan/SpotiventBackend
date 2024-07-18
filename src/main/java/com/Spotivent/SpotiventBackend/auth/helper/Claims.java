package com.Spotivent.SpotiventBackend.auth.helper;


import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

@Log
public class Claims {
    public static Map<String, Object> getClaims() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication auth = securityContext.getAuthentication();
    log.info(auth.toString());
    Jwt jwt = (Jwt) auth.getPrincipal();
    return jwt.getClaims();

    }
}
