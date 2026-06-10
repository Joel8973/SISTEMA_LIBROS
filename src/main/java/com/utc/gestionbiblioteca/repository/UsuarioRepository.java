package com.utc.gestionbiblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.gestionbiblioteca.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por username
    Optional<Usuario> findByUsername(String username);

    // Buscar usuario por correo
    Optional<Usuario> findByCorreo(String correo);

    // Buscar usuarios por nombre
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}