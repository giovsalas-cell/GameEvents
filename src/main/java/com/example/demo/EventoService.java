package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private static final String ESTAC = "Estacionamiento";
    private static final String LOCO = "Locomoción colectiva";
    private static final String COMIDA = "Servicio de comidas";
    private static final String ALOJ = "Alojamiento";

    private final List<Evento> eventos = List.of(
            new Evento(1L, "Santiago Game Fest", "Convención", LocalDate.now().plusDays(10),
                    LocalTime.of(10, 0), "Santiago", "Av. Libertador Bernardo O'Higgins 1000",
                    "La convención gamer más grande de la ciudad, con torneos, stands y zona de prueba de juegos.",
                    true, "Comunidad Gamer Chile",
                    List.of(ESTAC, LOCO, COMIDA), List.of("Camila Rojas (Game Designer)", "Diego Soto (Streamer)")),
            new Evento(2L, "Torneo Valorant Open", "Torneo", LocalDate.now().plusDays(5),
                    LocalTime.of(15, 30), "Valparaíso", "Av. Errázuriz 500",
                    "Torneo abierto por equipos con premios en dinero y transmisión en vivo.",
                    true, "Liga Esports Valpo",
                    List.of(LOCO, COMIDA), List.of("Fernanda Díaz (Caster)", "Matías Vera (Coach profesional)")),
            new Evento(3L, "Noche Retro Arcade", "Retro", LocalDate.now().plusDays(3),
                    LocalTime.of(20, 0), "Santiago", "Calle Providencia 1234",
                    "Una noche de juegos clásicos en máquinas arcade, con concurso de puntajes.",
                    false, "Arcade Club Santiago",
                    List.of(LOCO, COMIDA), List.of("Pablo Núñez (Coleccionista de arcades)")),
            new Evento(4L, "LAN Party Sur", "LAN Party", LocalDate.now().plusDays(20),
                    LocalTime.of(9, 0), "Concepción", "Av. Los Carrera 800",
                    "Fin de semana de juego en red. Trae tu equipo y tu energía.",
                    false, "Gamers del Sur",
                    List.of(ESTAC, COMIDA, ALOJ), List.of("Valentina Mora (Organizadora de LAN)")),
            new Evento(5L, "Expo Indie Chile", "Convención", LocalDate.now().plusDays(30),
                    LocalTime.of(11, 0), "Viña del Mar", "Av. San Martín 300",
                    "Estudios independientes muestran sus juegos al público y reciben comentarios.",
                    true, "Asociación de Desarrolladores Indie",
                    List.of(ESTAC, LOCO, COMIDA, ALOJ),
                    List.of("Sofía Araya (Desarrolladora indie)", "Tomás Pino (Productor)")),
            new Evento(6L, "Copa FIFA Universitaria", "Torneo", LocalDate.now().plusDays(14),
                    LocalTime.of(16, 0), "Santiago", "Av. Vicuña Mackenna 4860",
                    "Campeonato de fútbol virtual entre universidades.",
                    false, "Federación de Estudiantes",
                    List.of(ESTAC, LOCO), List.of("Andrés Cortés (Comentarista deportivo)")));

    public List<Evento> todos() {
        return eventos;
    }

    public List<Evento> destacados() {
        return eventos.stream()
                .filter(Evento::destacado)
                .sorted(Comparator.comparing(Evento::fecha))
                .toList();
    }

    public List<Evento> proximos() {
        return eventos.stream()
                .sorted(Comparator.comparing(Evento::fecha))
                .limit(4)
                .toList();
    }

    public List<Evento> buscar(String categoria, LocalDate fecha, String ubicacion) {
        return eventos.stream()
                .filter(e -> categoria == null || categoria.isBlank()
                        || e.categoria().equalsIgnoreCase(categoria))
                .filter(e -> fecha == null || e.fecha().equals(fecha))
                .filter(e -> ubicacion == null || ubicacion.isBlank()
                        || e.ubicacion().toLowerCase().contains(ubicacion.trim().toLowerCase()))
                .sorted(Comparator.comparing(Evento::fecha))
                .toList();
    }

    public Optional<Evento> porId(Long id) {
        return eventos.stream().filter(e -> e.id().equals(id)).findFirst();
    }

    public List<String> categorias() {
        return eventos.stream().map(Evento::categoria).distinct().sorted().toList();
    }
}