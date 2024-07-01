package com.ldt.api.controller;

import com.ldt.api.dto.UserDTO;
import com.ldt.api.entity.User;
import com.ldt.api.security.ResponseHelper;
import com.ldt.api.service.UserService;
import com.ldt.api.util.SecurityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * add user
     */

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user) {
        userService.addUser(user);

        return ResponseHelper.responseMsg("success add user", HttpStatus.OK);
    }

    @GetMapping("/info")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/current-user")
    public User getCurrentUser() {
        return SecurityUtil.getCurrentUser();
    }

    /**
     * get users as list
     */

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * get user by id
     */

    @GetMapping("/get")
    public User getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    /**
     * update user
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);

        return ResponseEntity.noContent().build();
    }

    /**
     * delete user
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * update name
     */

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userService.updateName(id, userDTO);

        return ResponseEntity.noContent().build();
    }

}
