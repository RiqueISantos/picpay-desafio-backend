package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.UserRequest;
import com.picpay.picpay_challenge.controller.response.UserResponse;
import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.mapper.UserMapper;
import com.picpay.picpay_challenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Users", description = "Gerenciamento de usuários")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Lista todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<UserResponse>>findAllUsers(){
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList());
    }

    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        User existingUser = userService.findUserById(id);
        return ResponseEntity.ok(UserMapper.toUserResponse(existingUser));
    }

    @Operation(summary = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso")
    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
        User user = userService.save(UserMapper.toUser(userRequest));
        return ResponseEntity.ok(UserMapper.toUserResponse(user));
    }

    @Operation(summary = "Atualiza um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User user = userService.updateUser(UserMapper.toUser(userRequest), id);
        return ResponseEntity.ok(UserMapper.toUserResponse(user));
    }

    @Operation(summary = "Exclui um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
