package com.decagontasks.fashionblogapi.repository;

import com.decagontasks.fashionblogapi.dto.PostDTO;
import com.decagontasks.fashionblogapi.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostModel, Long> {
    boolean existsByTitle(String title);

}
