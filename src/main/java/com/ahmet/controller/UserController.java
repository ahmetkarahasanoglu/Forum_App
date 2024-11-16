package com.ahmet.controller;

import com.ahmet.entity.User;
import com.ahmet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {
    
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.save(newUser);
    }

    @GetMapping("/{userId}")
    public User getById (@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateUser(userId, newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    
}
