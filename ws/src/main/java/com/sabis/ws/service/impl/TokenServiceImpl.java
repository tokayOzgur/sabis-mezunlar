package com.sabis.ws.service.impl;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabis.ws.dto.requests.CredentialsRequest;
import com.sabis.ws.dto.responses.GetUserByEmailResponse;
import com.sabis.ws.exception.UserNotFoundException;
import com.sabis.ws.model.Token;
import com.sabis.ws.model.User;
import com.sabis.ws.repository.UserRepository;
import com.sabis.ws.service.TokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(TokenServiceImpl.class);
    private final ObjectMapper objectmapper;

    @Override
    public Token generateToken(GetUserByEmailResponse user, CredentialsRequest credentials) {
        User inDb = userRepository.findById(user.getId()).orElseThrow(() -> {
            logger.error("User not found: " + user.getId());
            return new UserNotFoundException(user.getId());
        });
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(inDb);
        token.setExpirationDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
        redisTemplate.opsForValue().set(token.getToken(), token);
        logger.info("Token generated: {}", token.getToken());
        return token;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        logger.debug("Authorization header: " + authorizationHeader);
        var tokenInDb = extractToken(authorizationHeader);
        if (tokenInDb.isPresent() && !tokenInDb.get().isExpired() && tokenInDb.get().isActive())
            return tokenInDb.get().getUser();

        logger.error("Token not found or expired: " + authorizationHeader);
        return null;
    }

    @Override
    public void logout(String authorizationHeader) {
        var tokenInDb = extractToken(authorizationHeader);
        if (!tokenInDb.isPresent())
            return;
        tokenInDb.get().setActive(false);
        redisTemplate.opsForValue().set(tokenInDb.get().getToken(), tokenInDb.get());
    }

    private Optional<Token> extractToken(String authorizationHeader) {
        if (authorizationHeader == null) {
            logger.error("Authorization header is null");
            return Optional.empty();
        }
        String token = authorizationHeader.split(" ")[1];
        Object tokenData = redisTemplate.opsForValue().get(token);

        if (tokenData instanceof LinkedHashMap) {
            Token tokenInDb = this.objectmapper.convertValue(tokenData, Token.class);
            return Optional.ofNullable(tokenInDb);
        } else if (tokenData instanceof Token) {
            return Optional.ofNullable((Token) tokenData);
        } else {
            logger.error("Token data is not in the expected format: " + tokenData);
            return Optional.empty();
        }
    }

    @Override
    public Token findToken(String cookieValue) {
        Object tokenData = redisTemplate.opsForValue().get(cookieValue);
        if (tokenData == null) {
            logger.error("Token not found: " + cookieValue);
            return null;
        } else if (tokenData instanceof LinkedHashMap) {
            Token token = this.objectmapper.convertValue(tokenData, Token.class);
            return token;
        } else if (tokenData instanceof Token) {
            return (Token) tokenData;
        } else {
            logger.error("Token data is not in the expected format: " + tokenData);
            return null;
        }
    }

    @Override
    public void updateExpirationDate(Token token) {
        token.setExpirationDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
        redisTemplate.opsForValue().set(token.getToken(), token);
    }

    @Override
    public void updateTokenUser(int userId) {
        User updatedUser = userRepository.findById(userId).orElseThrow(() -> {
            logger.error("User not found: " + userId);
            return new UserNotFoundException(userId);
        });
        Token token = findTokenByUserId(userId);
        if (token != null) {
            token.setUser(updatedUser);
            redisTemplate.opsForValue().set(token.getToken(), token);
        }
        logger.info("Token updated for user: " + userId);
    }

    @Override
    public Token findTokenByUserId(int userId) {
        for (String key : redisTemplate.keys("*")) {
            Object tokenData = redisTemplate.opsForValue().get(key);
            Token token = null;
            if (tokenData instanceof LinkedHashMap) {
                token = this.objectmapper.convertValue(tokenData, Token.class);
            } else if (tokenData instanceof Token) {
                token = (Token) tokenData;
            }
            if (token != null && token.isActive() && !token.isExpired() && token.getUser().getId() == userId) {
                return token;
            }
        }
        return null;
    }
}
