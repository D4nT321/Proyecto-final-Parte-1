package app;

import gestion_clientes.model.Usuario;
import service.UsuarioService;
import gestion_clientes.model.Rol;


/**
 * Clase Main
 * 
 * Punto de inicio del programa.
 * Aquí se simula el uso del sistema de gestión de usuarios.
 */
public class App {
    public static void main(String[] args) throws Exception {

        // =========================
        // CREACIÓN DEL SERVICIO
        // =========================

        // Creamos el servicio que manejará los usuarios
        UsuarioService usuarioService = new UsuarioService();

        // =========================
        // CREACIÓN DE USUARIOS
        // =========================

        // Creamos un usuario administrador
        Usuario admin = new Usuario(
            1,
            "Administrador General",
            "admin",
            "1234",
            Rol.ADMINISTRADOR
        );

        // Creamos un usuario estándar
        Usuario user1 = new Usuario(
            2,
            "Juan Pérez",
            "juan",
            "abcd",
            Rol.ESTANDAR
        );

        // =========================
        // REGISTRO DE USUARIOS
        // =========================

        // El administrador registra usuarios en el sistema
        usuarioService.crearUsuario(admin, admin);
        usuarioService.crearUsuario(admin, user1);

        // =========================
        // INICIO DE SESIÓN
        // =========================

        // Intento de login correcto
        Usuario sesion = usuarioService.login("juan", "abcd");

        if (sesion != null) {
            System.out.println("Sesión iniciada por: " + sesion.getNombreCompleto());
        }

        // =========================
        // INTENTOS FALLIDOS (BLOQUEO)
        // =========================

        // Intentos fallidos para bloquear la cuenta
        usuarioService.login("juan", "mal1");
        usuarioService.login("juan", "mal2");
        usuarioService.login("juan", "mal3");

        // =========================
        // CAMBIO DE ROL
        // =========================

        // El administrador cambia el rol del usuario
        usuarioService.cambiarRol(admin, 2, Rol.ADMINISTRADOR);

        // =========================
        // BÚSQUEDA AVANZADA
        // =========================

        usuarioService.buscarPorNombre("juan");
        usuarioService.buscarPorRol(Rol.ADMINISTRADOR);

        // =========================
        // AUDITORÍA GLOBAL
        // =========================

        usuarioService.verAuditoriaGlobal(admin);

        // =========================
        // FIN DEL PROGRAMA
        // =========================

        System.out.println("Programa finalizado.");
    }
}
       
