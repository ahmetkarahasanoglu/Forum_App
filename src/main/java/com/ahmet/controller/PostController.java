package com.ahmet.controller;

import com.ahmet.dto.request.PostCreateRequestDto;
import com.ahmet.dto.request.PostUpdateRequestDto;
import com.ahmet.dto.response.PostResponseDto;
import com.ahmet.entity.Post;
import com.ahmet.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PostController {

    private final PostService postService;

    /**
     * Gets all posts or; gets all posts of a specific user when a
     * param is given (using param (optional param))
     */
    @GetMapping
    public List<PostResponseDto> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getById(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @PostMapping
    public PostResponseDto createPost(@RequestBody PostCreateRequestDto dto) {
        return postService.createPost(dto);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto dto) {
        return postService.updatePost(postId, dto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

}
