package com.example.CinemaProjecto.controllersThymeleaf;

import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.services.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
@RequiredArgsConstructor
public class ThymeleafCinemaController {
    private final CinemaService cinemaService;

    @GetMapping("/cinemas/view/all")
    public String getAllCinemas(Model model) {
        model.addAttribute("cinemas", cinemaService.getAll());
        return "cinemas";
    }

    @GetMapping("/cinemas/view/{id}")
    public String getCinemaDetails(@PathVariable Long id, Model model) {
        try {
            CinemaDto cinema = cinemaService.getById(id);
            model.addAttribute("cinema", cinema);
            return "cinema-detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Cinema not found");
            return "error";
        }
    }
}
