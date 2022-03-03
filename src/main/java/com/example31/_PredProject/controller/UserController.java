package com.example31._PredProject.controller;

import com.example31._PredProject.model.User;
import com.example31._PredProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String getAll(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "list";
    }

    @GetMapping("/user/{name}")
    public String getUserById(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", userService.findByUsername(name));
        return "getuserbyid";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit";
    }

    @PatchMapping("admin/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id")
            long id) {
        System.out.println("s");
        userService.update(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

}