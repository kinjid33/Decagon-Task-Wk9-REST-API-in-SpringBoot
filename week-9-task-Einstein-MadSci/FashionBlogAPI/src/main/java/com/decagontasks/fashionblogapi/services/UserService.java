package com.decagontasks.fashionblogapi.services;

import com.decagontasks.fashionblogapi.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    String saveUser(UserDTO user);

    String deleteUser(Long adminId, Long userIdtoDelete);

    List<UserDTO> findAll(Long adminId);

    UserDTO logInUser(UserDTO userDTO);

    UserDTO updateUserDetails(Long adminId, Long id, Map<String, Object> userDetailsUpdate);

    UserDTO blockUser(Long adminId, Long userIdToBlock);
}