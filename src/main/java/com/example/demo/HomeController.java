package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final EventoService eventoService;

    public HomeController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        model.addAttribute("nombreSitio", "Gamer Events");
        model.addAttribute("destacados", eventoService.destacados());
        model.addAttribute("proximos", eventoService.proximos());
        return "home";
    }
}