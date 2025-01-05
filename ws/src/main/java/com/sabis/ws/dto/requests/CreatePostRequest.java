package com.sabis.ws.dto.requests;

import com.sabis.ws.validation.file.FileType;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {

    @Size(max = 5000, message = "{sabis.constraints.post.size}")
    private String content;

    @FileType(types = { "jpeg", "png" })
    private String image;

    @FileType(types = { "mp4" })
    private String video;

}
