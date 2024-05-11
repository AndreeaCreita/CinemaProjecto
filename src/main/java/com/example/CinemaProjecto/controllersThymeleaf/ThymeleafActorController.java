package com.example.CinemaProjecto.controllersThymeleaf;
import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.services.ActorService;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ThymeleafActorController {
    private final ActorService actorService;

    @GetMapping("/actors/view/{actorId}")
    public String getActorPage(@PathVariable Long actorId, Model model){
        try{
            ActorDto actor = actorService.getActorById(actorId);
            if(actor == null)
            {
                return "error";
            }
            model.addAttribute("actor", actor);
            return "actor";
        }catch(Exception e){
            model.addAttribute("errorMessage", "And error occured");
            return "error";
        }

    }
}
