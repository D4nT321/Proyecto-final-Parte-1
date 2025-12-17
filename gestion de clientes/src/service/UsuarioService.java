package service;

import gestion_clientes.model.Usuario;
import gestion_clientes.model.Rol;
  
/**
 * Clase que maneja la lógica del sistema
 * Gestiona las operaciones sobre usuarios
 */
    public class UsuarioService {

    private Usuario[] usuarios;
    private int cantidadUsuarios;

    public UsuarioService() {
        usuarios = new Usuario[50]; // máximo 50 usuarios
        cantidadUsuarios = 0;
    }

     /**
      * Busca un usuario por ID
     * Busqueda (No requiere permiso)
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

    /**
     * Crear un nuevo usuario solo(ADMIN)
     */
    public boolean crearUsuario(Usuario solicitante,Usuario nuevoUsuario) {

        if (solicitante.getRol() != Rol.ADMINISTRADOR) {
     
            return false; 
        }

        // Verificar ID y username únicos
        if (cantidadUsuarios >= usuarios.length) {
            return false;
        }

            if (buscarPorId(nuevoUsuario.getId()) != null ||
            buscarPorUsername(nuevoUsuario.getUsername()) != null) {
            return false;
        }

        usuarios[cantidadUsuarios] = nuevoUsuario;
        cantidadUsuarios++;

        solicitante.registrarAccion(
             "Creó al usuario " + nuevoUsuario.getUsername()
         );
        return true;
    }

   

    /**
     * Eliminar usuario solo por (ADM)
     */
    public boolean eliminarUsuario(Usuario solicitante, int id) {

        
    if (solicitante.getRol() != Rol.ADMINISTRADOR) {
              
      return false;
         }
            
        for (int i = 0; i < cantidadUsuarios; i++) {
         if (usuarios[i].getId() == id) {

         solicitante.registrarAccion(
             "Eliminó al usuario con ID " + id
            );

    for (int j = i; j < cantidadUsuarios - 1; j++) {
         usuarios[j] = usuarios[j + 1];
     }
        usuarios[cantidadUsuarios - 1] = null;
        cantidadUsuarios--;
        return true;
      }
    }
        return false;
    }

     // Actualizar nombre (ADMIN o el mismo usuario)
    public boolean actualizarNombre(Usuario solicitante, int id, String nuevoNombre) {

        Usuario usuario = buscarPorId(id);
        if (usuario == null) return false;

        if (solicitante.getRol() == Rol.ADMINISTRADOR ||
            solicitante.getId() == usuario.getId()) {

            usuario.setNombreCompleto(nuevoNombre);
                solicitante.registrarAccion(
            "Actualizó el nombre del usuario con ID " + id
             );
             return true;
        }

        return false;
    }

    /**
 * Simula el inicio de sesión
 */
public Usuario login(String username, String password) {

    Usuario usuario = buscarPorUsername(username);

    if (usuario == null) {
        System.out.println("Usuario no encontrado.");
        return null;
    }

    if (usuario.verificarPassword(password)) {
        usuario.registrarAccion("Inició sesión");
        System.out.println("Inicio de sesión exitoso.");
        return usuario;
    } else {
        System.out.println("Contraseña incorrecta.");
        return null;
    }
}

    }
