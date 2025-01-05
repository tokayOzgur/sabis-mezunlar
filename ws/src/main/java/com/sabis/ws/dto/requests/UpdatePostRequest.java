package com.sabis.ws.dto.requests;

import com.sabis.ws.validation.file.FileType;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostRequest {

    @Size(max = 5000, message = "{sabis.constraints.post.size}")
    private String content;
    @FileType(types = { "jpeg", "png" })
    private String imageUrl;
    @FileType(types = { "mp4" })
    private String videoUrl;

}
