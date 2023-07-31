package com.decagontasks.fashionblogapi.dto;

import com.decagontasks.fashionblogapi.model.PostModel;
import com.decagontasks.fashionblogapi.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String comment;
    private UserModel user;
    private PostModel post;
}
