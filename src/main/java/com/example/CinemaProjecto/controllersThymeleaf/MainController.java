package com.example.CinemaProjecto.controllersThymeleaf;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping({"","/","/home"})
    public ModelAndView getHome(){

        return new ModelAndView("main");
    }



}