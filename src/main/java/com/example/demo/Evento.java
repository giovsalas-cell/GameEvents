package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record Evento(Long id, String nombre, String categoria, LocalDate fecha, LocalTime hora,
        String ubicacion, String direccion, String descripcion, boolean destacado,
        String organizadores, List<String> servicios, List<String> expositores) {

    public String fechaTexto() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String horaTexto() {
        return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}