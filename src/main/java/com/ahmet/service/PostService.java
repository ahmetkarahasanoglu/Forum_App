package com.ahmet.service;

import com.ahmet.dto.request.PostCreateRequestDto;
import com.ahmet.dto.request.PostUpdateRequestDto;
import com.ahmet.dto.response.LikeResponse;
import com.ahmet.dto.response.PostResponseDto;
import com.ahmet.repository.IPostRepository;
import com.ahmet.entity.Post;
import com.ahmet.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final IPostRepository repository;
    private final UserService userService;
    private final LikeService likeService;

    public List<PostResponseDto> getAllPosts(Optional<Long> userId) {
        List<Post> postList;
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        if(userId.isPresent()) {
            postList = repository.findAllByUserId(userId.get());
        }else {
            postList = repository.findAll();
        }
        postList.forEach(post -> {
            List<LikeResponse> postLikes = likeService.getAllLikesWithParam(Optional.of(post.getId()), Optional.empty());
            PostResponseDto postResponseDto = new PostResponseDto(post, postLikes);
            postResponseDtoList.add(postResponseDto);
        });
        return postResponseDtoList;
    }


    public PostResponseDto createPost(PostCreateRequestDto dto) {
        User user = userService.getById(dto.getUserId());
        if(user == null) {
            return null; //Custom Exception Ekle buraya !!!!!!!!!!!!!!!!
        }else {
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .text(dto.getText())
                    .user(user)
                    .build();
            Post savedPost = repository.save(post);
            PostResponseDto postResponseDto = new PostResponseDto(savedPost, null);
            return postResponseDto;
        }
    }

    public Post updatePost(Long postId, PostUpdateRequestDto dto) {
        Optional<Post> existingPostOptional = repository.findById(postId);
        if(existingPostOptional.isEmpty()) {
            return null; //Custom Exception Ekle buraya !!!!!!!!!!!!!!!!
        }else{
            existingPostOptional.get().setTitle(dto.getTitle());
            existingPostOptional.get().setText(dto.getText());
            return repository.save(existingPostOptional.get());
        }

    }

    public void deletePost(Long postId) {
        repository.deleteById(postId);
    }


    public Post getById(Long postId) {
        //Custom Exception Ekle: !!!!!!!!!!!!!!!!!!!!!!!!
        return repository.findById(postId).orElse(null);
    }
}
