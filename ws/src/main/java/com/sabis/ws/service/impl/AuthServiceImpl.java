package com.sabis.ws.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sabis.ws.config.CurrentUser;
import com.sabis.ws.dto.requests.CredentialsRequest;
import com.sabis.ws.dto.responses.AuthResponse;
import com.sabis.ws.dto.responses.GetUserByEmailResponse;
import com.sabis.ws.dto.responses.GetUserByIdResponse;
import com.sabis.ws.exception.AccessDeniedException;
import com.sabis.ws.exception.AuthenticationException;
import com.sabis.ws.model.Token;
import com.sabis.ws.service.AuthService;
import com.sabis.ws.service.ModelMapperService;
import com.sabis.ws.service.TokenService;
import com.sabis.ws.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapperService mapper;
    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    @Override
    public AuthResponse authenticate(CredentialsRequest credentials) {
        GetUserByEmailResponse inDB = userService.findByEmail(credentials.email());
        if (inDB == null || !passwordEncoder.matches(credentials.password(), inDB.getPassword())) {
            logger.error("Invalid credentials for user: {}", credentials.email());
            throw new AuthenticationException();
        } else if (!inDB.isActive() || inDB.isDeleted()) {
            logger.error("User is not active or deleted: {}", credentials.email());
            throw new AuthenticationException();
        }
        GetUserByIdResponse userResp = mapper.forResponse().map(inDB, GetUserByIdResponse.class);
        Token token = tokenService.generateToken(inDB, credentials);
        return AuthResponse.builder().token(token).user(userResp).build();
    }

    @Override
    public void logout(String authorizationHeader) {
        tokenService.logout(authorizationHeader);
    }

    @Override
    public AuthResponse getCurrentUser(String cookieValue) {
        Token token = tokenService.findToken(cookieValue);
        if (token == null) {
            logger.error("Token not found");
            throw new AuthenticationException();
        } else if (token.isExpired() && !token.isActive()) {
            logger.error("Token is expired or inactive");
            throw new AccessDeniedException();
        }
        tokenService.updateExpirationDate(token);
        GetUserByIdResponse userResp = mapper.forResponse().map(token.getUser(), GetUserByIdResponse.class);
        return AuthResponse.builder().user(userResp).build();
    }

    public int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CurrentUser) authentication.getPrincipal()).getId();
    }
}
