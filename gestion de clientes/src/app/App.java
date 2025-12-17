package app;

import gestion_clientes.model.Usuario;
import service.UsuarioService;
import gestion_clientes.model.Rol;

public class App {
    public static void main(String[] args) throws Exception {

         // Creamos el servicio principal
        UsuarioService service = new UsuarioService();

        // Creamos usuarios
        Usuario admin = new Usuario(
                1,
                "Administrador General",
                "admin",
                "admin123",
                Rol.ADMINISTRADOR
        );

        Usuario user1 = new Usuario(
                2,
                "Laura Torres",
                "laura",
                "1234",
                Rol.ESTANDAR
        );

        Usuario user2 = new Usuario(
                3,
                "Carlos Pérez",
                "carlos",
                "abcd",
                Rol.ESTANDAR
        );

        // El administrador crea los usuarios
        service.crearUsuario(admin, admin);
        service.crearUsuario(admin, user1);
        service.crearUsuario(admin, user2);

        // -------- SIMULACIÓN DE SESIONES --------

        // Sesión 1: Laura inicia sesión correctamente
        Usuario sesionLaura = service.login("laura", "1234");
        if (sesionLaura != null) {
            service.actualizarNombre(sesionLaura, 2, "Laura T.");
        }

        // Sesión 2: Carlos se equivoca de contraseña
        Usuario sesionCarlos = service.login("carlos", "xxxx");

        // Sesión 3: Carlos intenta de nuevo correctamente
        sesionCarlos = service.login("carlos", "abcd");

        // Sesión 4: Admin inicia sesión
        Usuario sesionAdmin = service.login("admin", "admin123");
        if (sesionAdmin != null) {
            service.eliminarUsuario(sesionAdmin, 2); // elimina a Laura
        }

        // -------- MOSTRAR HISTORIALES --------
        System.out.println("\n--- HISTORIALES ---\n");
        admin.mostrarHistorial();
        user1.mostrarHistorial();
        user2.mostrarHistorial(); 
        
    }
}

      
