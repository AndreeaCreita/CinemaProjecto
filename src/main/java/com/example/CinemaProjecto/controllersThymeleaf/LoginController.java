package com.example.CinemaProjecto.controllersThymeleaf;

import org.springframework.ui.Model;
import com.example.CinemaProjecto.dtos.UserLoginDto;
import com.example.CinemaProjecto.dtos.UserRegisterDto;
import com.example.CinemaProjecto.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {


    private final UserService service;

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping("/login_handler")
    String loginHandler(UserLoginDto userLogin){
        var user = service.loginUser(userLogin);
        if(user != null){
            return "main";
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register_handler")
    public String registerHandler(UserRegisterDto userRegister, Model model) {
        if (service.registerUser(userRegister)) {
            return "main"; // Redirect to login page on successful registration
        } else {
            model.addAttribute("error", "Email already in use or invalid data.");
            return "register"; // Stay on the registration page and display error
        }
    }


}