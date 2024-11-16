package com.ahmet.dto.response;

import com.ahmet.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {

    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String text;
    private List<LikeResponse> postLikes;

    public PostResponseDto(Post entity, List<LikeResponse> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = likes;
    }

}
