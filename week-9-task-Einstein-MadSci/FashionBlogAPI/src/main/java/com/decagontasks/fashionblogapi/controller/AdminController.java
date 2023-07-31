package com.decagontasks.fashionblogapi.controller;

import com.decagontasks.fashionblogapi.dto.PostDTO;
import com.decagontasks.fashionblogapi.dto.UserDTO;
import com.decagontasks.fashionblogapi.services.PostService;
import com.decagontasks.fashionblogapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private PostService postService;

    @Autowired
    public AdminController (UserService userService, PostService postService){
        this.userService = userService;
        this.postService = postService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long idToDelete, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long adminId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(userService.deleteUser(adminId, idToDelete));
    }

    @PostMapping("/block-user/{id}")
    public ResponseEntity<UserDTO> blockUser(@PathVariable("id") Long idToBlock, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long adminId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(userService.blockUser(adminId, idToBlock));
    }

    @GetMapping("/view-all-users")
    public ResponseEntity<List<UserDTO>> displayAllUsers(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long adminId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(userService.findAll(adminId));
    }

    @PatchMapping("/update-user-details/{id}")
    public ResponseEntity<UserDTO> updateUserDetails(@PathVariable("id") Long idToUpdate, @RequestBody Map<String, Object> userDetailsUpdate, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long adminId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(userService.updateUserDetails(adminId, idToUpdate, userDetailsUpdate));
    }

    @PostMapping("/add-post")
    public ResponseEntity<PostDTO> createPost(/*@PathVariable("id") Long id,*/ @RequestBody PostDTO postDTO, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long id = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(postService.createPost(postDTO, id));
    }

    @PatchMapping("/update-post/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long postId, @RequestBody Map<String, Object> postUpdate, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(postService.updatePost(userId, postId, postUpdate));
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId, HttpServletRequest httpServletRequest){
        HttpSession  session = httpServletRequest.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(postService.deletePost(userId, postId));
    }
}
