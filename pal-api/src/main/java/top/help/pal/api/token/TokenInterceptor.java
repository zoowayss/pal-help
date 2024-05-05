package top.help.pal.api.token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.help.pal.common.token.TokenUser;

import java.io.IOException;
import java.lang.reflect.Parameter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class TokenInterceptor implements HandlerInterceptor {

    public static final String TOKEN_USER_ATTRIBUTE = "TOKEN_USER";

    private static final String UNAUTHORIZED_PROMPT = "{\"success\":false, \"code\":\"401\", \"msg\": \"Unauthorized\"}";

    public static final String MISSING_DID = "{\"success\":false,\"code\":\"401\",\"msg\":\"Invalid request. missing request header: did\"}";

    private TokenVerifier verifier;

    public TokenInterceptor(TokenVerifier tokenVerifier) {
        this.verifier = tokenVerifier;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        // 检查是否有TokenUser参数
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        boolean hasTokenUserParam = false;
        boolean isTokenRequired = false;

        for (Parameter parameter : parameters) {
            if (parameter.getType().equals(TokenUser.class)) {
                hasTokenUserParam = true;
                isTokenRequired = parameter.isAnnotationPresent(TokenRequired.class);
                break;
            }
        }

        if (!hasTokenUserParam) {
            return true;
        }
        String did = request.getHeader("did");
        String ip = request.getRemoteAddr();
        TokenUser user;

        if (!StringUtils.hasText(did)) {
            writeUnauthorized(response, MISSING_DID);
            return false;
        }

        String token = request.getHeader(AUTHORIZATION);
        user = StringUtils.hasText(token) ? verifier.verifyAndGetUserId(token) : null;

        if (isTokenRequired) {
            if (user == null) {
                writeUnauthorized(response, UNAUTHORIZED_PROMPT);
                return false;
            }
        } else if (user == null) {
            user = new TokenUser(0L, did, ip);
        }

        user.setIp(ip);

        request.setAttribute(TOKEN_USER_ATTRIBUTE, user);
        return true;
    }

    private void writeUnauthorized(HttpServletResponse response, String prompt) throws IOException {
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(prompt);
    }
}
