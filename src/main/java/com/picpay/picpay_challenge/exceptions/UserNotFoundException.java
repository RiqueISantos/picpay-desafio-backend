package com.picpay.picpay_challenge.exceptions;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(Long id){
        super("Usuário com ID: " + id + " não encontrado.");
    }
}
