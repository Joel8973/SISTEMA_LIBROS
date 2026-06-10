package com.utc.gestionbiblioteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    // Llave primaria de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del usuario
    @Column(nullable = false)
    private String nombre;

    // Apellido del usuario
    @Column(nullable = false)
    private String apellido;

    // Correo único
    @Column(nullable = false, unique = true)
    private String correo;

    // Teléfono
    private String telefono;

    // Nombre de usuario para iniciar sesión
    @Column(nullable = false, unique = true)
    private String username;

    // Contraseña
    @Column(nullable = false)
    private String password;

    // Rol del usuario (ADMIN, BIBLIOTECARIO, USUARIO)
    private String rol;

    // Estado activo o inactivo
    private Boolean estado = true;

    // Constructor vacío requerido por JPA
    public Usuario() {
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}