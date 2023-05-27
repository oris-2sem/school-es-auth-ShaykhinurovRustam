package ru.itis.ediary.security.utils;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationsHeaderUtil {
    boolean hasAuthorizationToken(HttpServletRequest request);

    String getToken(HttpServletRequest request);
}
