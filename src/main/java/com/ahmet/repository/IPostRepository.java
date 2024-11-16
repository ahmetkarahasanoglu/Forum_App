package com.ahmet.repository;

import com.ahmet.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUserId(Long userId); // (JpaRepository handles the "ByUserId". Because we mapped the User in Post class).

}
