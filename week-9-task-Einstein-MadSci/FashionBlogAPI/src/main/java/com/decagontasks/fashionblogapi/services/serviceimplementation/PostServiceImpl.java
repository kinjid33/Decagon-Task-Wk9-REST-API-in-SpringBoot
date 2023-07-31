package com.decagontasks.fashionblogapi.services.serviceimplementation;

import com.decagontasks.fashionblogapi.dto.PostDTO;
import com.decagontasks.fashionblogapi.model.PostModel;
import com.decagontasks.fashionblogapi.model.UserModel;
import com.decagontasks.fashionblogapi.repository.PostRepo;
import com.decagontasks.fashionblogapi.repository.UserRepo;
import com.decagontasks.fashionblogapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepo postRepo;
    private UserRepo userRepo;

    @Autowired
    public PostServiceImpl (PostRepo postRepo, UserRepo userRepo){
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, Long adminId) {
        UserModel adminUser = userRepo.findById(adminId).orElseThrow(
                () -> new NullPointerException("No user with id " + adminId));
        if(!adminUser.getRole().equalsIgnoreCase("admin")){
            throw new RuntimeException("You can not create a post");
        }
        PostModel posts = new PostModel(postDTO);
        posts.setUser(adminUser);
        postRepo.save(posts);
        return postEntityToDTO(posts);
    }

    @Override
    public List<PostDTO> findAll(){
        List<PostModel> allPosts = postRepo.findAll();
        return allPosts.stream().map(this::postEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO findById(Long postId){
        PostModel post = postRepo.findById(postId)
                .orElseThrow(() -> new NullPointerException("No post with id " + postId));
        return postEntityToDTO(post);
    }

    @Override
    public PostDTO updatePost(Long adminId, Long postId, Map<String, Object> postUpdate) {
        UserModel adminUser = userRepo.findById(adminId).orElseThrow(
                () -> new NullPointerException("No user with id " + adminId));
        if(!adminUser.getRole().equalsIgnoreCase("admin")){
            throw new RuntimeException("You can not update a post");
        }
        Optional<PostModel> postToUpdate = postRepo.findById(postId);
        postToUpdate.ifPresent(postModel -> postUpdate.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(PostModel.class, key);
            ReflectionUtils.makeAccessible((field));
            ReflectionUtils.setField(field, postToUpdate.get(), value);
        }));
        PostModel updatedPost = postRepo.save(postToUpdate.get());
        return postEntityToDTO(updatedPost);
    }

    @Override
    public String deletePost(Long adminId, Long postId){
        UserModel adminUser = userRepo.findById(adminId).orElseThrow(
                () -> new NullPointerException("No user with id " + adminId));
        if(!adminUser.getRole().equalsIgnoreCase("admin")){
            throw new RuntimeException("You can not delete a post");
        }
        try {
            postRepo.deleteById(postId);
        } catch (Exception ex) {
            return "Post not found";
        }
        return "Post deleted";
    }

    public PostDTO postEntityToDTO(PostModel postModel){
        return PostDTO.builder()
                .id(postModel.getId())
                .title(postModel.getTitle())
                .post(postModel.getPost())
                .likes(postModel.getLikes())
                .user(postModel.getUser())
                .build();
    }
}