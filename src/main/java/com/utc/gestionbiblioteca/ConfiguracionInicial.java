package com.utc.gestionbiblioteca;

// Importa el servicio para trabajar con usuarios
import org.springframework.beans.factory.annotation.Autowired;

// Permite ejecutar código automáticamente al iniciar Spring Boot
import org.springframework.boot.CommandLineRunner;

// Indica que esta clase será administrada por Spring
import org.springframework.stereotype.Component;

// Entidad Usuario
import com.utc.gestionbiblioteca.entity.Usuario;

// Servicio de usuarios
import com.utc.gestionbiblioteca.service.UsuarioService;

/*
 * @Component
 * Hace que Spring detecte esta clase automáticamente
 * al iniciar la aplicación.
 */
@Component
public class ConfiguracionInicial implements CommandLineRunner {

    /*
     * Inyección automática del servicio de usuarios.
     * Nos permitirá consultar y guardar usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;

    /*
     * Este método se ejecuta automáticamente
     * cada vez que se inicia el proyecto.
     */
    @Override
    public void run(String... args) throws Exception {

        /*
         * Busca si ya existe un usuario con username "admin"
         */
        Usuario adminExistente =
                usuarioService.buscarPorUsername("admin");

        /*
         * Si no existe el administrador,
         * lo crea automáticamente.
         */
        if (adminExistente == null) {

            // Crear nuevo objeto Usuario
            Usuario admin = new Usuario();

            // Datos personales
            admin.setNombre("Administrador");
            admin.setApellido("Sistema");
            admin.setCorreo("admin@biblioteca.com");
            admin.setTelefono("000000000");

            // Credenciales para iniciar sesión
            admin.setUsername("admin");
            admin.setPassword("admin123");

            // Rol del usuario
            admin.setRol("ADMIN");

            /*
             * Guarda el administrador
             * en la base de datos.
             */
            usuarioService.guardarUsuario(admin);

            // Mensaje en consola
            System.out.println(
                    "Administrador creado correctamente.");
        }

    }

}
