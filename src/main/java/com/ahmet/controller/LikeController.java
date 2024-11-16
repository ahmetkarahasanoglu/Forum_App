package com.ahmet.controller;

import com.ahmet.dto.request.LikeCreateRequestDto;
import com.ahmet.dto.response.LikeResponse;
import com.ahmet.entity.Like;
import com.ahmet.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public List<LikeResponse> getAllLikesWithParam(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return likeService.getAllLikesWithParam(postId, userId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequestDto dto) {
        return likeService.createLike(dto);
    }

    @GetMapping("/{likeId}")
    public Like getById(@PathVariable Long likeId) {
        return likeService.getById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteById(@PathVariable Long likeId) {
        likeService.deleteById(likeId);
    }

}
