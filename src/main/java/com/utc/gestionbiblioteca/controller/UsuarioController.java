package com.utc.gestionbiblioteca.controller;

// Importaciones necesarias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.utc.gestionbiblioteca.entity.Usuario;
import com.utc.gestionbiblioteca.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /*
     * =====================================================
     * HU01 - INICIAR SESIÓN
     * =====================================================
     */

    // Muestra login.html
    @GetMapping("/")
    public String mostrarLogin() {

        return "login";
    }

    // Procesa el login
    @PostMapping("/login")
    public String iniciarSesion(

            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Usuario usuario =
                usuarioService.buscarPorUsername(username);

        // Verifica usuario y contraseña
        if (usuario != null &&
                usuario.getPassword().equals(password)) {

            // Verifica que sea administrador
            if ("ADMIN".equalsIgnoreCase(usuario.getRol())) {

                return "menu";
            }

            model.addAttribute(
                    "error",
                    "No tiene permisos para acceder.");

            return "login";
        }

        model.addAttribute(
                "error",
                "Usuario o contraseña incorrectos.");

        return "login";
    }

    /*
     * =====================================================
     * HU03 - CONSULTAR USUARIOS
     * =====================================================
     */

    // Mostrar todos los usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {

        model.addAttribute(
                "usuarios",
                usuarioService.listarUsuarios());

        return "usuarios";
    }

    /*
     * =====================================================
     * HU02 - REGISTRAR USUARIOS
     * =====================================================
     */

    // Mostrar formulario de registro
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioRegistro(Model model) {

        model.addAttribute(
                "usuario",
                new Usuario());

        return "registrarUsuario";
    }

    // Guardar usuario
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(

            @ModelAttribute Usuario usuario,
            Model model) {

        /*
         * VALIDACIÓN NOMBRE
         */
        if (usuario.getNombre() == null ||
                usuario.getNombre().trim().isEmpty()) {

            model.addAttribute(
                    "error",
                    "Debe ingresar el nombre.");

            return "registrarUsuario";
        }

        /*
         * VALIDACIÓN APELLIDO
         */
        if (usuario.getApellido() == null ||
                usuario.getApellido().trim().isEmpty()) {

            model.addAttribute(
                    "error",
                    "Debe ingresar el apellido.");

            return "registrarUsuario";
        }

        /*
         * VALIDACIÓN CORREO
         */
        if (usuario.getCorreo() == null ||
                usuario.getCorreo().trim().isEmpty()) {

            model.addAttribute(
                    "error",
                    "Debe ingresar el correo.");

            return "registrarUsuario";
        }

        /*
         * VALIDACIÓN USUARIO
         */
        if (usuario.getUsername() == null ||
                usuario.getUsername().trim().isEmpty()) {

            model.addAttribute(
                    "error",
                    "Debe ingresar el nombre de usuario.");

            return "registrarUsuario";
        }

        /*
         * VALIDACIÓN CONTRASEÑA
         */
        if (usuario.getPassword() == null ||
                usuario.getPassword().length() < 6) {

            model.addAttribute(
                    "error",
                    "La contraseña debe tener mínimo 6 caracteres.");

            return "registrarUsuario";
        }

        /*
         * VALIDAR CORREO ÚNICO
         */
        Usuario correoExistente =
                usuarioService.buscarPorCorreo(
                        usuario.getCorreo());

        if (correoExistente != null) {

            model.addAttribute(
                    "error",
                    "El correo ya está registrado.");

            return "registrarUsuario";
        }

        /*
         * VALIDAR USUARIO ÚNICO
         */
        Usuario usuarioExistente =
                usuarioService.buscarPorUsername(
                        usuario.getUsername());

        if (usuarioExistente != null) {

            model.addAttribute(
                    "error",
                    "El nombre de usuario ya existe.");

            return "registrarUsuario";
        }

        /*
         * Si todo está correcto
         * guarda el usuario
         */
        usuarioService.guardarUsuario(usuario);

        /*
         * Mensaje de éxito
         */
        model.addAttribute(
                "mensaje",
                "Usuario registrado correctamente.");

        /*
         * Recargar lista
         */
        model.addAttribute(
                "usuarios",
                usuarioService.listarUsuarios());

        return "usuarios";
    }

}