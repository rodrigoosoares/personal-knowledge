package com.personal.knowledge.personalknowledge.controllers.interceptors;

import com.personal.knowledge.personalknowledge.controllers.validation.annotations.AppAccessValidate;
import com.personal.knowledge.personalknowledge.controllers.validation.exceptions.UnauthorizedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Objects;

public class ApplicationBasicTokenInterceptor implements HandlerInterceptor {

    public static final String APP_ACCESS_FIELD = "app-access";
    private static final String INVALID_TOKEN_ERROR_MESSAGE = "Token format is invalid.";
    private static final String CREDENTIALS_ERROR_MESSAGE = "Invalid credentials to access application.";

    private final String user;
    private final String password;

    public ApplicationBasicTokenInterceptor(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(hasAppAuthorization(handler)) {
            final String appAccessToken = request.getHeader(APP_ACCESS_FIELD);

            if(Objects.isNull(appAccessToken) || !isValidToken(appAccessToken)) {
                throw new UnauthorizedException(CREDENTIALS_ERROR_MESSAGE, APP_ACCESS_FIELD);
            }
        }
        return true;
    }

    private boolean isValidToken(final String appAccessToken) throws IllegalArgumentException {
        try {
            final String decodedToken = new String(Base64.getDecoder().decode(appAccessToken));
            return validateCredentials(decodedToken);
        } catch (final Exception e) {
            // TODO LOG
            throw new UnauthorizedException(INVALID_TOKEN_ERROR_MESSAGE, APP_ACCESS_FIELD);
        }
    }

    private boolean validateCredentials(final String decodedToken) {
        final String[] credentials = decodedToken.split(":");
        return user.equals(credentials[0]) && password.equals(credentials[1]);
    }

    private boolean hasAppAuthorization(final Object handler) {
        return ((HandlerMethod) handler).getMethod().isAnnotationPresent(AppAccessValidate.class);
    }
}
