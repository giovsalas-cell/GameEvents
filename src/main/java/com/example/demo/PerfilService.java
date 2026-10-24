package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PerfilService {

    public static final List<String> AVATARES = List.of("🎮", "👾", "🕹️", "🐉", "🤖", "🦊");

    public static final List<String> JUEGOS = List.of(
            "Valorant", "League of Legends", "Minecraft", "Fortnite",
            "Counter-Strike 2", "FIFA", "Elden Ring", "Zelda");

    private final Map<String, Perfil> perfiles = new ConcurrentHashMap<>();

    public Perfil obtener(String email) {
        Perfil guardado = perfiles.get(email);
        return guardado != null ? copiar(guardado) : porDefecto(email);
    }

    public void guardar(String email, Perfil perfil) {
        perfiles.put(email, copiar(perfil));
    }

    private Perfil porDefecto(String email) {
        Perfil p = new Perfil();
        String base = email.split("@")[0].replaceAll("[^A-Za-z0-9_]", "_");
        if (base.length() > 20) {
            base = base.substring(0, 20);
        }
        while (base.length() < 3) {
            base += "_";
        }
        p.setNombreUsuario(base);
        p.setAvatar(AVATARES.get(0));
        return p;
    }

    private Perfil copiar(Perfil o) {
        Perfil p = new Perfil();
        p.setNombreUsuario(o.getNombreUsuario());
        p.setAvatar(o.getAvatar());
        p.setJuegos(o.getJuegos() == null ? new ArrayList<>() : new ArrayList<>(o.getJuegos()));
        p.setNotifNuevosEventos(o.isNotifNuevosEventos());
        p.setNotifRecordatorios(o.isNotifRecordatorios());
        p.setNotifPublicidad(o.isNotifPublicidad());
        return p;
    }
}