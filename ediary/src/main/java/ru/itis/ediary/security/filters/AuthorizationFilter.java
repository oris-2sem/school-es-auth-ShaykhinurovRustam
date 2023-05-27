package ru.itis.ediary.security.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.ediary.security.utils.AuthorizationsHeaderUtil;
import ru.itis.ediary.security.utils.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    public static final String AUTHENTICATION_PATH = "/auth/token";
    private final AuthorizationsHeaderUtil authorizationsHeaderUtil;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(AUTHENTICATION_PATH)) {
            filterChain.doFilter(request, response);
        } else {
            if (authorizationsHeaderUtil.hasAuthorizationToken(request)) {
                String token = authorizationsHeaderUtil.getToken(request);

                try {
                    Map<String, String> data = jwtUtil.parseToken(token);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(data.get("login"),
                            null, Collections.singletonList(new SimpleGrantedAuthority(data.get("role"))));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);

                } catch (JWTVerificationException e) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
