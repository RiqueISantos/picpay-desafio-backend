package com.picpay.picpay_challenge.service;

import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.exceptions.UserNotFoundException;
import com.picpay.picpay_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(User user, Long id){
        User existingUser = findUserById(id);

        user.setId(id);
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
