package com.example.controller;

import com.example.repository.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        User user = userRepository.findByUserName(userName);

        if (user != null && user.getPassword().equals(password)) {
            return "welcome";
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }
    }
}
