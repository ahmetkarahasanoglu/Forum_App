package com.ahmet.dto.response;

import com.ahmet.entity.Like;
import lombok.Data;

@Data
public class LikeResponse {

    private Long id;
    private Long userId;
    private Long postId;

    // CONSTRUCTOR (for Entity-to-Response Conversion):
    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }

}
