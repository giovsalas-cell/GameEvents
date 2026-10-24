package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class Perfil {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9_]*$", message = "Solo letras, números y guion bajo")
    private String nombreUsuario;

    private String avatar;

    private List<String> juegos = new ArrayList<>();

    private boolean notifNuevosEventos = true;
    private boolean notifRecordatorios = true;
    private boolean notifPublicidad = false;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<String> juegos) {
        this.juegos = juegos;
    }

    public boolean isNotifNuevosEventos() {
        return notifNuevosEventos;
    }

    public void setNotifNuevosEventos(boolean v) {
        this.notifNuevosEventos = v;
    }

    public boolean isNotifRecordatorios() {
        return notifRecordatorios;
    }

    public void setNotifRecordatorios(boolean v) {
        this.notifRecordatorios = v;
    }

    public boolean isNotifPublicidad() {
        return notifPublicidad;
    }

    public void setNotifPublicidad(boolean v) {
        this.notifPublicidad = v;
    }
}