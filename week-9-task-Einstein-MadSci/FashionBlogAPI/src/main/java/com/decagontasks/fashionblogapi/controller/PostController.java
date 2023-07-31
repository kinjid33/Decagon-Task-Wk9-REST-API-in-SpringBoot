package com.decagontasks.fashionblogapi.controller;

import com.decagontasks.fashionblogapi.dto.PostDTO;
import com.decagontasks.fashionblogapi.services.PostService;
import com.decagontasks.fashionblogapi.services.serviceimplementation.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
//@RequiredArgsConstructor
public class PostController {
    private PostService postService;

    @Autowired
    public PostController (PostService postService){
        this.postService = postService;
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<PostDTO>> viewAllPosts(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/single-post/{id}")
    public ResponseEntity<PostDTO> viewSinglePost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.findById(id));
    }
}