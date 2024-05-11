package com.example.CinemaProjecto.controllersThymeleaf;
import com.example.CinemaProjecto.dtos.UserDto;
import com.example.CinemaProjecto.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ThymeleafUserController {

    private final UserService userService;

    @GetMapping("/users/view/{userId}")
    public String getUserPage(@PathVariable Long userId, Model model) {
        try {
            UserDto user = userService.getUser(userId);
            if (user == null) {
                return "user-not-found";
            }
            model.addAttribute("user", user);
            return "user";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while trying to fetch user details.");
            return "error";
        }
    }
}