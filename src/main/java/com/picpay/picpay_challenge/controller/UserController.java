package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.response.UserResponse;
import com.picpay.picpay_challenge.mapper.UserMapper;
import com.picpay.picpay_challenge.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public ResponseEntity<List<UserResponse>>findAllUsers(){
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList());
    }

    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id)
                .map(UserMapper::toUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não existe!")));
    }

}
