package com.decagontasks.fashionblogapi.repository;

import com.decagontasks.fashionblogapi.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepo extends JpaRepository<CommentModel, Long> {
    Optional<CommentModel> findCommentModelByUser_Id(Long userId);
}
