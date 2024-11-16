package com.ahmet.repository;

import com.ahmet.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepository extends JpaRepository<Like,Long> {

    List<Like> findAllByPostIdAndUserId(Long postId, Long userId);
    List<Like> findAllByPostId(Long postId);
    List<Like> findAllByUserId(Long userId);
}
