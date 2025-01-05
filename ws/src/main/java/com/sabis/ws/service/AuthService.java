package com.sabis.ws.service;

import com.sabis.ws.dto.requests.CredentialsRequest;
import com.sabis.ws.dto.responses.AuthResponse;

/**
 * @author tokay
 */
public interface AuthService {

    AuthResponse authenticate(CredentialsRequest credentials);

    void logout(String authorizationHeader);

    AuthResponse getCurrentUser(String cookieValue);

    int getCurrentUserId();
}
