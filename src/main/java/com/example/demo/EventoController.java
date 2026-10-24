package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public String listar(@RequestParam(required = false) String categoria,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) String ubicacion,
            Model model) {
        model.addAttribute("eventos", eventoService.buscar(categoria, fecha, ubicacion));
        model.addAttribute("categorias", eventoService.categorias());
        model.addAttribute("categoriaSel", categoria);
        model.addAttribute("fechaSel", fecha);
        model.addAttribute("ubicacionSel", ubicacion);
        return "eventos";
    }

    @GetMapping("/eventos/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return eventoService.porId(id)
                .map(e -> {
                    model.addAttribute("evento", e);
                    return "evento";
                })
                .orElse("redirect:/eventos");
    }
}