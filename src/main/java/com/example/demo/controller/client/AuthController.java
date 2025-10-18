package com.example.demo.controller.client;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute("registerUser") @Valid RegisterDTO registerUser,
                           BindingResult bindingResult, Model model) {
        // log errors
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            System.out.println(">>> " + fieldError.getField() + " - " + fieldError.getDefaultMessage());
        }

        // check error
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User newUser = userMapper.toUser(registerUser);
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        newUser.setRole(userService.getRoleByName("USER"));
        userService.handleSaveUser(newUser);
        return "redirect:/login";
    }
}
