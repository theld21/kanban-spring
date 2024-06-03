package com.ldt.api.service;

import com.ldt.api.dto.UserDTO;
import com.ldt.api.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getUsers();

    User getUser(Integer id);

    User getUserByEmail(String email);

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);

    void updateName(Integer id, UserDTO userDTO);
}
