package com.example.fitnesproject.controllers;

import com.example.fitnesproject.domain.Users;
import com.example.fitnesproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final String NEW_USER_ATTR = "new_user";
    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String registration(Model model){
        model.addAttribute(NEW_USER_ATTR, new Users());
        //System.out.println(model.getAttribute("user"));
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute(NEW_USER_ATTR) Users users) {
        userService.addUser(users);
        return "redirect:/login";
    }
}
