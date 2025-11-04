package com.picpay.picpay_challenge.mapper;

import com.picpay.picpay_challenge.controller.request.UserRequest;
import com.picpay.picpay_challenge.controller.response.UserResponse;
import com.picpay.picpay_challenge.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest userRequest){
        return User
                .builder()
                .fullName(userRequest.fullName())
                .cpf(userRequest.cpf())
                .email(userRequest.email())
                .password(userRequest.password())
                .typeUser(userRequest.typeUser())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse
                .builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .typeUser(user.getTypeUser())
                .build();
    }

}
