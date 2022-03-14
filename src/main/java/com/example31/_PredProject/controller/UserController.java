package com.example31._PredProject.controller;

import com.example31._PredProject.model.Role;
import com.example31._PredProject.model.User;
import com.example31._PredProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "list";
    }

    @GetMapping("/admin")
    public String getAll(@AuthenticationPrincipal UserDetails userDetails,
                         Model model) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("roleList", user.getRoles());
        return "list";
    }

    @GetMapping("/user")
    public String getUserById(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "redirect:/admin";
    }

    @PatchMapping("admin/edit/{id}")
    public String update(@ModelAttribute("user") User newUser, @PathVariable("id") @RequestParam(value = "checked", required = false) Long checked,
                         long id) {
        if (checked == 2L) {
            newUser.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        } else {
            newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        }
        userService.save(newUser);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/new")
    public String addUser(@ModelAttribute User newUser,
                          @RequestParam(value = "checked", required = false) Long checked) {
        if (checked == 2L) {
            newUser.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        } else {
            newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        }
        userService.save(newUser);
        return "redirect:/admin";
    }
}