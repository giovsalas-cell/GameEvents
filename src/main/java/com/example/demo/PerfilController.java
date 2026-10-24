package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/perfil")
    public String ver(Authentication auth, Model model) {
        model.addAttribute("perfil", perfilService.obtener(auth.getName()));
        cargarOpciones(model);
        return "perfil";
    }

    @PostMapping("/perfil")
    public String guardar(@Valid @ModelAttribute("perfil") Perfil perfil,
            BindingResult result,
            Authentication auth,
            Model model) {

        // Validación en el servidor: solo se aceptan valores de las listas permitidas
        if (perfil.getAvatar() == null || !PerfilService.AVATARES.contains(perfil.getAvatar())) {
            result.rejectValue("avatar", "invalido", "Elige un avatar de la lista");
        }
        if (perfil.getJuegos() != null
                && !PerfilService.JUEGOS.containsAll(perfil.getJuegos())) {
            result.rejectValue("juegos", "invalido", "Hay juegos que no son válidos");
        }

        if (result.hasErrors()) {
            cargarOpciones(model);
            return "perfil";
        }

        perfilService.guardar(auth.getName(), perfil);
        return "redirect:/perfil?guardado";
    }

    private void cargarOpciones(Model model) {
        model.addAttribute("avatares", PerfilService.AVATARES);
        model.addAttribute("juegosDisponibles", PerfilService.JUEGOS);
    }
}