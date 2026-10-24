package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    private final InMemoryUserDetailsManager manager;
    private final PasswordEncoder encoder;

    public RegistroController(InMemoryUserDetailsManager manager, PasswordEncoder encoder) {
        this.manager = manager;
        this.encoder = encoder;
    }

    @GetMapping("/registro")
    public String formulario(Model model) {
        model.addAttribute("registroForm", new RegistroForm());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("registroForm") RegistroForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "registro";
        }
        String email = form.getEmail().trim().toLowerCase();
        if (manager.userExists(email)) {
            result.rejectValue("email", "duplicado", "Ya existe una cuenta con ese correo");
            return "registro";
        }
        manager.createUser(User.builder()
                .username(email)
                .password(encoder.encode(form.getPassword()))
                .roles("USER")
                .build());
        return "redirect:/login?registrado";
    }
}