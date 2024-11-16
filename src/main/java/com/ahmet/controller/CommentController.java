package com.ahmet.controller;

import com.ahmet.dto.request.CommentCreateRequestDto;
import com.ahmet.entity.Comment;
import com.ahmet.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) { // These parameters are optional; It can be given, or not. ||| Params are stated in the path.
        return commentService.getAllCommentsWithParam(postId, userId);                                              // '--> Since we are getting data, we used '@GetMapping'. And we used '@RequestParam' (we can't use @RequestBody with @GetMapping)
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequestDto dto) {
        return commentService.createComment(dto);
    }

    @GetMapping("/{commentId}")
    public Comment getById(@PathVariable Long commentId) {
        return commentService.getById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody String newText) {
        return commentService.updateComment(commentId, newText);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

}
