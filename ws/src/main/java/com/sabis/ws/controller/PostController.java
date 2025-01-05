package com.sabis.ws.controller;

import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sabis.ws.dto.requests.CreatePostRequest;
import com.sabis.ws.dto.requests.UpdatePostRequest;
import com.sabis.ws.dto.responses.GetAllActivePostResponse;
import com.sabis.ws.dto.responses.GetAllPostByUserIdResponse;
import com.sabis.ws.dto.responses.GetAllPostResponse;
import com.sabis.ws.dto.responses.GetPostByIdResponse;
import com.sabis.ws.service.AuthService;
import com.sabis.ws.service.PostService;
import com.sabis.ws.shared.GenericMessage;
import com.sabis.ws.shared.Messages;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    private final AuthService authService;

    public PostController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }

    @PostMapping
    public GenericMessage createPost(@RequestBody CreatePostRequest postRequest) {
        postService.save(postRequest, authService.getCurrentUserId());
        return new GenericMessage(Messages.getMessageForLocale("sabis.create.post.success.message",
                LocaleContextHolder.getLocale()));
    }

    @PutMapping("/{postId}")
    public GenericMessage updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest postRequest) {
        postService.updateByPostId(postId, postRequest);
        return new GenericMessage(Messages.getMessageForLocale("sabis.update.post.success.message",
                LocaleContextHolder.getLocale()));
    }

    @DeleteMapping("/{postId}")
    public GenericMessage deletePost(@PathVariable Long postId) {
        postService.deleteByPostId(postId);
        return new GenericMessage(Messages.getMessageForLocale("sabis.delete.post.success.message",
                LocaleContextHolder.getLocale()));
    }

    @GetMapping
    public Page<GetAllActivePostResponse> getAllPost(Pageable pageable) {
        return postService.findByIsDeletedFalse(pageable);
    }

    @GetMapping("/all")
    public Page<GetAllPostResponse> getAllPostWithNotActive(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @GetMapping("/{postId}")
    public GetPostByIdResponse getPostByPostId(@PathVariable Long postId) {
        return postService.findById(postId);
    }

    @GetMapping("/user/{userId}")
    public Page<GetAllPostByUserIdResponse> getPostListByUserId(@PathVariable int userId, Pageable pageable) {
        return postService.findByUserId(pageable, userId);
    }

    @GetMapping("/users")
    public Page<GetAllPostByUserIdResponse> getPostListByUserIds(@RequestParam List<Integer> userIds,
            Pageable pageable) {
        return postService.findByUserIds(pageable, userIds);
    }

}
