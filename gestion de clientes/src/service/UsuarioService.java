package service;

import gestion_clientes.model.Usuario;
import gestion_clientes.model.Rol;
  

/**
 * Clase UsuarioService
 * 
 * Esta clase se encarga de manejar toda la lógica del sistema
 * relacionada con los usuarios:
 * - Crear usuarios
 * - Buscar usuarios
 * - Iniciar sesión
 * - Validar permisos
 * - Cambiar roles
 * - Auditoría global
 */
    public class UsuarioService {

// ALMACENAMIENTO DE USUARIOS

// Arreglo donde se guardan los usuarios del sistema
    private Usuario[] usuarios;
// Cantidad actual de usuarios registrados
    private int cantidadUsuarios;


 /**
* Constructor del servicio
* Inicializa el arreglo de usuarios
 */
    public UsuarioService() {
        usuarios = new Usuario[50]; // máximo 50 usuarios
        cantidadUsuarios = 0;
    }

 // CREAR USUARIO

  /**
* Crea un nuevo usuario en el sistema
* Solo debe ser llamado por un administrador
 */
    public boolean crearUsuario(Usuario admin,Usuario nuevoUsuario) {

        // Verificar que el usuario que crea sea administrador
        if (admin.getRol() != Rol.ADMINISTRADOR) {
        System.out.println("No tienes permisos para crear usuarios.");
            return false; 
        }

// Verificar que el username no esté repetido
         if (buscarPorUsername(nuevoUsuario.getUsername()) != null) {
            System.out.println("El username ya existe.");
            return false;
        }

    // Verificar espacio en el arreglo
        if (cantidadUsuarios >= usuarios.length) {
            System.out.println("No se pueden registrar más usuarios.");
            return false;
        }
    
     // Agregar el usuario al arreglo
        usuarios[cantidadUsuarios] = nuevoUsuario;
        cantidadUsuarios++;

    // Registrar acción
     // Registrar acción
        admin.registrarAccion(
            "Creó el usuario " + nuevoUsuario.getUsername()
        );

        return true;
    }

    // BÚSQUEDAS

    /**
    *Busca un usuario por ID
    */
    public Usuario buscarPorId(int id) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getId() == id) {
                return usuarios[i];
            }
        }
        return null;
    }

    /**
     * Buscar usuario por username
     */
    public Usuario buscarPorUsername(String username) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getUsername().equals(username)) {
                return usuarios[i];
            }
        }
        return null;
    }

     // INICIO DE SESIÓN

    /**
 * Simula el inicio de sesión
 */
public Usuario login(String username, String password) {

// Buscar el usuario por username
    Usuario usuario = buscarPorUsername(username);

// Si no existe, el login falla
    if (usuario == null) {
        System.out.println("Usuario no encontrado.");
        return null;
    }

// Si la cuenta está bloqueada
  if (usuario.estaBloqueado()) {
            System.out.println("La cuenta está bloqueada.");
            return null;
        }

// Verificar contraseña
 if (usuario.verificarPassword(password)) {
        usuario.reiniciarIntentos();
        usuario.registrarAccion("Inició sesión");
        return usuario;
        }

        // Si la contraseña es incorrecta
        usuario.registrarIntentoFallido();
        System.out.println("Contraseña incorrecta.");
        return null;
    }

     // CAMBIO DE ROL
    /**
     * Permite a un administrador cambiar el rol de un usuario
     */
    public boolean cambiarRol(Usuario admin, int idUsuario, Rol nuevoRol) {

        // Verificar permisos
    if (admin.getRol() != Rol.ADMINISTRADOR) {
        System.out.println("No tienes permisos para cambiar roles.");    
      return false;
         }
        // Buscar el usuario
        Usuario usuario = buscarPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return false;
        }

         // Cambiar el rol
          usuario.setRol(nuevoRol);

        // Registrar acción del administrador
        admin.registrarAccion(
            "Cambió el rol del usuario " +
            usuario.getUsername() + " a " + nuevoRol
        );

        return true;
    }
         // BÚSQUEDA AVANZADA

         /** Busca usuarios cuyo nombre contenga un texto
         */

public void buscarPorNombre(String texto) {
    System.out.println("Usuarios encontrados con nombre que contiene");

    for (int i = 0; i < cantidadUsuarios; i++) {
        Usuario u = usuarios[i];

        // Convertimos a minúsculas para evitar errores por mayúsculas
        if (u.getNombreCompleto().toLowerCase().contains(texto.toLowerCase())) {
            System.out.println(
                 u.getId() + " - " +
                    u.getNombreCompleto() +
                    " (" + u.getRol() + ")"
            );
        }
    }
}
        /**
        * Busca usuarios por rol
        */
public void buscarPorRol(Rol rol) {
    System.out.println("Usuarios con rol" + rol + ":");

    for (int i = 0; i < cantidadUsuarios; i++) {
        Usuario u = usuarios[i];

        if (u.getRol() == rol) {
            System.out.println(
                u.getId() + " - " +
                    u.getNombreCompleto()
            );
        }
    }
}

     // AUDITORÍA GLOBAL

    /**
    * Permite a un administrador ver el historial 
    * de todos los usuarios del sistema
    */
    public void verAuditoriaGlobal(Usuario admin) {

    // Verificar permisos
    if (admin.getRol() != Rol.ADMINISTRADOR) {
        System.out.println("Acceso denegado. Solo administradores.");
        return;
    }

    System.out.println("=== AUDITORÍA GLOBAL DEL SISTEMA ===");

    // Recorremos todos los usuarios
    for (int i = 0; i < cantidadUsuarios; i++) {
        usuarios[i].mostrarHistorial();
    }

     // Registrar la acción del administrador
     admin.registrarAccion("Consultó la auditoría global");
    }
}

