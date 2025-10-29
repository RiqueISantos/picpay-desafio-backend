package com.picpay.picpay_challenge.service;

import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.exceptions.UserNotFoundException;
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
        User existingUser = findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setId(id);
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
