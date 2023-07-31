package com.decagontasks.fashionblogapi.model;

import com.decagontasks.fashionblogapi.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts_table")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String post;

    private Long likes;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "post_user_fk"
            )
    )
//    @JsonBackReference
    private UserModel user;

    public PostModel (PostDTO postDTO){
        this.title = postDTO.getTitle();
        this.post = postDTO.getPost();
        this.likes = postDTO.getLikes();
        this.user = postDTO.getUser();
    }
}