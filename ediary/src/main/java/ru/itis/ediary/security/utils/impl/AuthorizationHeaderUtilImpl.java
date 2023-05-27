package ru.itis.ediary.security.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.ediary.security.utils.AuthorizationsHeaderUtil;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthorizationHeaderUtilImpl implements AuthorizationsHeaderUtil {
    private static String AUTHORIZATION_HEADER = "Authorization";
    private static String BEARER = "Bearer ";

    @Override
    public boolean hasAuthorizationToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        return header.substring(BEARER.length());
    }
}