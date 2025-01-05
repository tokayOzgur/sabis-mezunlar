package com.sabis.ws.dto.responses;


import com.sabis.ws.model.Token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    GetUserByIdResponse user;
    Token token;

}
