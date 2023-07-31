package com.decagontasks.fashionblogapi.services;

import com.decagontasks.fashionblogapi.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Long adminId, Long postId);
    String deleteComment(Long adminId, Long commentId);
}
