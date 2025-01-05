package com.sabis.ws.dto.responses;

import lombok.Data;

@Data
public class GetUserByEmailResponse {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String image;
    private boolean active;
    private boolean isDeleted;
}
