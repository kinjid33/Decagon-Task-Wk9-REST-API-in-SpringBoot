package com.decagontasks.fashionblogapi.dto;

import com.decagontasks.fashionblogapi.model.CommentModel;
import com.decagontasks.fashionblogapi.model.UserModel;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;

    private String title;

    private String post;

    private Long likes;

    @ManyToOne
    private UserModel user;

//    @OneToMany
//    private List<CommentModel> comments;
}
