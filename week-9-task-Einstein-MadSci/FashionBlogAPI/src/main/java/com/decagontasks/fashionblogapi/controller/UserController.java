package com.decagontasks.fashionblogapi.controller;

import com.decagontasks.fashionblogapi.dto.CommentDTO;
import com.decagontasks.fashionblogapi.dto.UserDTO;
import com.decagontasks.fashionblogapi.services.CommentService;
import com.decagontasks.fashionblogapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private CommentService commentService;
    @Autowired
    public UserController(UserService userService, CommentService commentService){
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> RegisterUser(@RequestBody UserDTO userDTO){
       String response = userService.saveUser(userDTO);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUsers(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest){
        UserDTO user = userService.logInUser(userDTO);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user_id", user.getId());
        return ResponseEntity.ok(userService.logInUser(user));
    }

    @PostMapping("/write-comment/{id}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long adminId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(commentService.createComment(commentDTO, adminId, id));
    }

    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        return ResponseEntity.ok(commentService.deleteComment(userId, commentId));
    }
}