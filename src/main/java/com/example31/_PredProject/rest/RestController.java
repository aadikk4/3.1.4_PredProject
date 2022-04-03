package com.example31._PredProject.rest;

import com.example31._PredProject.model.User;
import com.example31._PredProject.service.UserDetailsServiceImp;
import com.example31._PredProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/admin")
    public List<User> getAllUsers(){
        return (List<User>) userService.allUsers();
    }

    @GetMapping("/user")
    public  User getUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return userDetailsServiceImp.findByUsername(username);
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id) {
         return userService.findUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "User with id = " + id + " was deleted";
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }
    @GetMapping("/userau")
    public User getUserPage(@AuthenticationPrincipal User user) {
        return user;
    }
}
