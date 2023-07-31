package com.decagontasks.fashionblogapi.model;

import com.decagontasks.fashionblogapi.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_table")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private boolean isBlocked;

    private boolean isDeleted;

//    @OneToMany
//    @JoinColumn(
//            name = "post_id",
//            nullable = false,
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "user_post_fk"
//            )
//    )
//    @JsonManagedReference
//    private List<PostModel> post;

//    @OneToMany
//    @JoinColumn(
//            name = "comment_id",
//            nullable = false,
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "user_comment_fk"
//            )
//    )
//    @JsonManagedReference
//    private List<CommentModel> comment;

    public UserModel (UserDTO userDTO){
        this.userName = userDTO.getUserName();
        this.role = userDTO.getRole();
        this.password = userDTO.getPassword();
        this.isBlocked = userDTO.isBlocked();
        this.isDeleted = userDTO.isDeleted();
    }
}