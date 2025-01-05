package com.sabis.ws.dto.responses;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllPostResponse {
    private String content;
    private String imageUrl;
    private String videoUrl;
    private Date creationTime;
    private boolean isDeleted;
    private int userId;
}
