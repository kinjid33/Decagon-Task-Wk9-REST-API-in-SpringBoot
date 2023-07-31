package com.decagontasks.fashionblogapi.services.serviceimplementation;

import com.decagontasks.fashionblogapi.dto.CommentDTO;
import com.decagontasks.fashionblogapi.model.CommentModel;
import com.decagontasks.fashionblogapi.model.PostModel;
import com.decagontasks.fashionblogapi.model.UserModel;
import com.decagontasks.fashionblogapi.repository.CommentRepo;
import com.decagontasks.fashionblogapi.repository.PostRepo;
import com.decagontasks.fashionblogapi.repository.UserRepo;
import com.decagontasks.fashionblogapi.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class CommentServiceImpl implements CommentService {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    public CommentServiceImpl(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo){
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long adminId, Long postId) {
        UserModel adminUser = userRepo.findById(adminId).orElseThrow(
                () -> new NullPointerException("No user with id " + adminId));
        PostModel postToCommentOn = postRepo.findById(postId).get();
        CommentModel comment = new CommentModel(commentDTO);
        comment.setUser(adminUser);
        comment.setPost(postToCommentOn);
        commentRepo.save(comment);
        return commentEntityToDTO(comment);
    }

    @Override
    public String deleteComment(Long adminId, Long commentId){
        UserModel adminUser = userRepo.findById(adminId).orElseThrow(
                () -> new NullPointerException("No user with id " + adminId));
        CommentModel comment = commentRepo.findById(commentId).orElseThrow(
                () -> new NullPointerException("No comment with id " + commentId));
        if(adminUser.getRole().equalsIgnoreCase("admin")){
            return deleteLogic(commentId);
        }
        CommentModel owner = commentRepo.findCommentModelByUser_Id(adminId).orElseThrow(
                () -> new NullPointerException("No user with id " + adminId)
        );
        if(!(owner == comment)){
            throw new RuntimeException("You can not delete another user's comment");
        }
//        if(!Objects.equals(adminUser.getComment(), comment)){
//            throw new RuntimeException("You can not delete another user's comment");
//        }
        return deleteLogic(commentId);
//        try {
//            commentRepo.deleteById(commentId);
//        } catch (Exception ex) {
//            return "Post not found";
//        }
//        return "Post deleted";
    }

    public CommentDTO commentEntityToDTO(CommentModel commentModel){
        return CommentDTO.builder()
                .id(commentModel.getId())
                .comment(commentModel.getComment())
                .user(commentModel.getUser())
                .post(commentModel.getPost())
                .build();
    }

    public String deleteLogic(Long commentId){
        try {
            commentRepo.deleteById(commentId);
        } catch (Exception ex) {
            return "Post not found";
        }
        return "Post deleted";
    }
}