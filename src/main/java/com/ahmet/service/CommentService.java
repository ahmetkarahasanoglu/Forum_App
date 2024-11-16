package com.ahmet.service;

import com.ahmet.dto.request.CommentCreateRequestDto;
import com.ahmet.repository.ICommentRepository;
import com.ahmet.entity.Comment;
import com.ahmet.entity.Post;
import com.ahmet.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ICommentRepository repository;
    private final UserService userService;
    private final PostService postService;

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(postId.isPresent() && userId.isPresent()) {
            return repository.findAllByPostIdAndUserId(postId.get(), userId.get());
        }else if(userId.isPresent()) {
            return repository.findAllByUserId(userId.get());
        }else if(postId.isPresent()) {
            return repository.findAllByPostId(postId.get());
        }else {
            return repository.findAll();
        }
    }

    public Comment getById(Long commentId) {
        // Custom Exception Ekle: !!!!!!!!!!!!!!
        return repository.findById(commentId).orElse(null);
    }


    public Comment createComment(CommentCreateRequestDto dto) {
        User user = userService.getById(dto.getUserId());
        Post post = postService.getById(dto.getPostId());
        if(user!=null && post!=null) { // (bu kontrol silinebilir bence, custom exception'ları yazdıktan sonra.)
            return repository.save(Comment.builder()
                    .text(dto.getText())
                    .user(user)
                    .post(post)
                    .build());
        }else {
            return null; // (custom exception ??)
        }
    }

    public Comment updateComment(Long commentId, String newText) {
        Optional<Comment> existingCommentOptional = repository.findById(commentId);
        if(existingCommentOptional.isPresent()) {
            existingCommentOptional.get().setText(newText);
            return repository.save(existingCommentOptional.get());
        }
        else {
            return null; // (custom exception ??)
        }
    }

    public void deleteComment(Long commentId) {
        repository.deleteById(commentId);
    }
}
