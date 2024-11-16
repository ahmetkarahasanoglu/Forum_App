package com.ahmet.service;

import com.ahmet.repository.IUserRepository;
import com.ahmet.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User save(User newUser) {
        return repository.save(newUser);
    }

    public User getById(Long userId) {
        //Custom Exception Ekle: !!!!!!!!!!!!!!!!!!!!!!!
        return repository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User newUser) {
        //Custom Exception Ekle: !!!!!!!!!!!!!!!!!!!!!!!
        User existingUser = repository.findById(userId).orElse(null);
        if(newUser.getUsername()!=null && !newUser.getUsername().isBlank()) {
            existingUser.setUsername(newUser.getUsername());
        }
        if(newUser.getPassword()!=null && !newUser.getPassword().isBlank()) {
            existingUser.setPassword(newUser.getPassword());
        }
        return repository.save(existingUser);
    }

    public void deleteUser(Long id) {
        // Custom Exception Ekle: !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        boolean isUserExist = repository.existsById(id);
        if(!isUserExist) {
            // (Custom Exception Ekle buraya)
        }else{
            repository.deleteById(id);
        }
    }



}
