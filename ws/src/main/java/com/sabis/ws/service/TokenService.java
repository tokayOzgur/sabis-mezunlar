package com.sabis.ws.service;

import com.sabis.ws.dto.requests.CredentialsRequest;
import com.sabis.ws.dto.responses.GetUserByEmailResponse;
import com.sabis.ws.model.Token;
import com.sabis.ws.model.User;

public interface TokenService {
    public Token generateToken(GetUserByEmailResponse user, CredentialsRequest credentials);

    public User verifyToken(String authorizationHeader);

    public void logout(String authorizationHeader);

    public Token findToken(String cookieValue);

    public void updateExpirationDate(Token token);

    public void updateTokenUser(int userId);

    public Token findTokenByUserId(int userId);
}
