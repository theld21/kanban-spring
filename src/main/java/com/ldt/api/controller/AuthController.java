package com.ldt.api.controller;

import com.ldt.api.entity.User;
import com.ldt.api.security.JWTHelper;
import com.ldt.api.security.JwtRequest;
import com.ldt.api.security.JwtResponse;
import com.ldt.api.security.ResponseHelper;
import com.ldt.api.service.UserService;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JWTHelper helper;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        User user = userService.getUserByEmail(email);
        if (Objects.equals(user.getPassword(), password)) {
            System.out.println("User: " + user);
        } else {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

        String token = this.helper.generateToken(user);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(user.getEmail()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity exceptionHandler() {
        return ResponseHelper.responseMsg("success add user", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/register")
    public ResponseEntity addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseHelper.responseMsg("success add user", HttpStatus.OK);
    }
}
