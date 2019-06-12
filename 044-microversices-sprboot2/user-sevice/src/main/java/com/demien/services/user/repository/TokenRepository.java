package com.demien.services.user.repository;

import com.demien.services.user.domain.Token;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenRepository {

    private final static Map<String, Token> USER_STORAGE = new HashMap<>();
    private final static Map<String, Token> TOKEN_STORAGE = new HashMap<>();

    public Token getTokenForUser(String userId) {
        synchronized (USER_STORAGE) {
            Token token = USER_STORAGE.get(userId);
            if (token!=null && isValid(token)) {
                return token;
            }
            // generation
            String tokenId=java.util.UUID.randomUUID().toString();
            token = new Token(tokenId, userId);
            synchronized (TOKEN_STORAGE) {
                USER_STORAGE.put(userId, token);
                TOKEN_STORAGE.put(tokenId, token);
            }
            return token;
        }
    }

    public String getUserIdByToken(String tokenId) {
        return TOKEN_STORAGE.get(tokenId).getUserId();
    }

    protected boolean isValid(Token token) {
        return true;
    }


}
