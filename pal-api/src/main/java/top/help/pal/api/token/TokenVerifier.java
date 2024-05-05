package top.help.pal.api.token;

import top.help.pal.common.token.TokenUser;

public interface TokenVerifier {

    TokenUser verifyAndGetUserId(String token);
}
