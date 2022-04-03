package com.example31._PredProject.controller;

import com.example31._PredProject.model.Role;
import com.example31._PredProject.model.User;
import com.example31._PredProject.service.RoleService;
import com.example31._PredProject.service.UserDetailsServiceImp;
import com.example31._PredProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/")
    public String index() {
        return "list";
    }

    @GetMapping("/admin")
    public String getAll(@AuthenticationPrincipal UserDetails userDetails,
                         Model model) {
        String username = userDetails.getUsername();
        User user = userDetailsServiceImp.findByUsername(username);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("roleList", user.getRoles());
        return "list";
    }

    @GetMapping("/user")
    public String getUserById(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userDetailsServiceImp.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "redirect:/admin";
    }

    @PatchMapping("admin/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") @RequestParam(name = "roles", required = false) String roles,  long id) {
        userService.save(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/new")
    public String addUser(@ModelAttribute User newUser,
                          @RequestParam(name = "roles", required = false) String roles) {
        userService.save(newUser, roles);
        return "redirect:/admin";
    }
}