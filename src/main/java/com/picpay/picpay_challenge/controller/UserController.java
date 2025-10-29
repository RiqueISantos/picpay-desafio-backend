package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.UserRequest;
import com.picpay.picpay_challenge.controller.response.UserResponse;
import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.mapper.UserMapper;
import com.picpay.picpay_challenge.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>>findAllUsers(){
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(UserMapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
        User user = userService.save(UserMapper.toUser(userRequest));
        return ResponseEntity.ok(UserMapper.toUserResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User user = userService.updateUser(UserMapper.toUser(userRequest), id);
        return ResponseEntity.ok(UserMapper.toUserResponse(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
