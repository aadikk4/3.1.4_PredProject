package com.example31._PredProject.rest;

import com.example31._PredProject.model.User;
import com.example31._PredProject.service.UserDetailsServiceImp;
import com.example31._PredProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/admin")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.allUsers());
    }

    @GetMapping("/user")
    public  ResponseEntity<User> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok().body(userDetailsServiceImp.findByUsername(username));
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
         return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
//    @GetMapping("/userau")
//    public User getUserPage(@AuthenticationPrincipal User user) {
//        return user;
//    }
}
