package com.ahmet.service;

import com.ahmet.dto.request.LikeCreateRequestDto;
import com.ahmet.dto.response.LikeResponse;
import com.ahmet.repository.ILikeRepository;
import com.ahmet.entity.Like;
import com.ahmet.entity.Post;
import com.ahmet.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final ILikeRepository repository;
    private final UserService userService;
    private final PostService postService;

    // CONSTRUCTOR. ('@Lazy' is for avoiding infinite calling each other of PostService and LikeService [-> in seperate files]. And I didn't use '@RequiredArgsConstructor' at the top of this page because I used a constructor so that I can use '@Lazy' in it.)
    public LikeService(@Lazy PostService postService, ILikeRepository repository, UserService userService) {
        this.postService = postService;
        this.repository = repository;
        this.userService = userService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> postId, Optional<Long> userId) {
        List<Like> likeList;
        if(postId.isPresent() && userId.isPresent()) {
            likeList = repository.findAllByPostIdAndUserId(postId.get(), userId.get());
        }else if(postId.isPresent()) {
            likeList = repository.findAllByPostId(postId.get());
        }else if(userId.isPresent()) {
            likeList = repository.findAllByUserId(userId.get());
        }else {
            likeList = repository.findAll();
        }
        List<LikeResponse> likeResponseList = likeList.stream()
                .map(like -> new LikeResponse(like))
                .collect(Collectors.toList());
        return likeResponseList;
    }

    public Like createLike(LikeCreateRequestDto dto) {
        Post post = postService.getById(dto.getPostId());
        User user = userService.getById(dto.getUserId());
        if(user != null && post != null) { // (checking if the relevant 'service' methods returned null [if the 'user' and 'post' couldn't be found])
            Like likeToSave = Like.builder()
                    .post(post)
                    .user(user)
                    .build();
            return repository.save(likeToSave);
        }else {
            return null; //Custom Exception Ekle: !!!!!!!!!!!!!!!!!!!!!!!!
        }

    }

    public Like getById(Long likeId) {
        //Custom Exception Ekle: !!!!!!!!!!!!!!!!!!!!!!!!
        return repository.findById(likeId).orElse(null);
    }

    public void deleteById(Long likeId) {
        repository.deleteById(likeId);
    }
}
