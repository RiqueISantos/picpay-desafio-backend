package com.picpay.picpay_challenge.service;

import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User saveUser){
        return userRepository.save(saveUser);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public User updateUser(User user, Long id){
        Optional<User> savedUser = findUserById(id);

        if(savedUser.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }
        throw new EntityNotFoundException("Usuário com ID " + id + " não existe!");
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
