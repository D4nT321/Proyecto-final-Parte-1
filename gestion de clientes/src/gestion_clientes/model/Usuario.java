package gestion_clientes.model;

/**
 * Representa un usuario del sistema
 */
public class Usuario {

//  Datos básicos del usuario (datos del usuario)

private int id;
private String nombreCompleto;
private String username;
private String password;
private Rol rol; 

//  Historial de acciones del usuario
    private Accion[] historial;
    private int cantidadAcciones;

/**
 * Constructor del Usuario 
 */
public Usuario(int id, String nombreCompleto, String username, String password, Rol rol) {
    this.id = id;
    this.nombreCompleto = nombreCompleto;
    this.username = username;
    this.password = password;
    this.rol = rol;

    // Inicializamos el historial
    historial = new Accion[100]; // máximo 100 acciones
    cantidadAcciones = 0; 
}

 // Métodos para consultar información (getters) 
 // Getters (para leer datos)
  public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

     public Rol getRol() {
        return rol;
    }

    // Permite cambiar el nombre del usuario
    // Setters (para modificar datos)
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
         registrarAccion("Actualizó su nombre completo");
    }

    /**
     * Cambia la contraseña solo si la actual es correcta
     */
    public boolean cambiarPassword(String passwordActual, String nuevaPassword) {
        if (this.password.equals(passwordActual)) {
            this.password = nuevaPassword;
            registrarAccion("Cambió su contraseña");
            return true;
        }
        return false;
    }

    /**
     * Verifica si la contraseña es correcta
     */
    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }

     // HISTORIAL

    public void registrarAccion(String descripcion) {
        if (cantidadAcciones < historial.length) {
            historial[cantidadAcciones] = new Accion(descripcion);
            cantidadAcciones++;
        }
    }

    public void mostrarHistorial() {
        System.out.println("Historial de acciones de " + nombreCompleto + ":");
        for (int i = 0; i < cantidadAcciones; i++) {
            System.out.println(
                "- " + historial[i].getDescripcion() +
                " | Tiempo: " + historial[i].getTimestamp()
            );
        }
    }

} 