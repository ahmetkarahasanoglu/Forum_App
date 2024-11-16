package com.ahmet.repository;

import com.ahmet.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPostIdAndUserId(Long postId, Long userId);
    List<Comment> findAllByUserId(Long userId);
    List<Comment> findAllByPostId(Long postId);

}
