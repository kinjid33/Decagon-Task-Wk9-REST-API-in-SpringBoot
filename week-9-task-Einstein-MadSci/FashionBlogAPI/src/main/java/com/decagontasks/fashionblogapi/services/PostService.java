package com.decagontasks.fashionblogapi.services;

import com.decagontasks.fashionblogapi.dto.PostDTO;

import java.util.List;
import java.util.Map;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Long id);

    List<PostDTO> findAll();

    PostDTO findById(Long id);

    PostDTO updatePost(Long userId, Long postId, Map<String, Object> postUpdate);

    String deletePost(Long userId, Long postId);
}