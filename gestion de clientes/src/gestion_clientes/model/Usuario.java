package gestion_clientes.model;

/**
 * Representa un usuario del sistema
 * Clase Usuario
 * Contiene sus datos, rol y el historial de acciones.
 */
public class Usuario {

//  Datos básicos del usuario (datos del usuario)

// Identificador único del usuario
private int id;
// Nombre completo del usuario
private String nombreCompleto;
// Nombre de usuario para iniciar sesión (único)
private String username;
// Contraseña del usuario (texto simple, solo para el ejercicio)
private String password;
 // Rol del usuario (ADMINISTRADOR o ESTANDAR)
private Rol rol; 

//  Historial de acciones del usuario

// Arreglo donde se guardan las acciones realizadas por el usuario
    private Accion[] historial;
// Cantidad actual de acciones registradas  
    private int cantidadAcciones;

    // Seguridad de la cuenta

// Indica si la cuenta está bloqueada
private boolean bloqueado;
// Cuenta los intentos fallidos de inicio de sesión
private int intentosFallidos;

/**
 * Constructor de la clase Usuario
 * Se ejecuta cuando se crea un nuevo usuario
 */
public Usuario(int id, String nombreCompleto, String username, String password, Rol rol) {
    this.id = id;
    this.nombreCompleto = nombreCompleto;
    this.username = username;
    this.password = password;
    this.rol = rol;

// Inicializamos el historial con un tamaño máximo de 100 acciones
    historial = new Accion[100]; // máximo 100 acciones
    cantidadAcciones = 0; 

// Estado inicial de la cuenta
bloqueado = false;
intentosFallidos = 0;

}

 // Métodos para consultar información (getters) 
 // Getters (para leer datos)

  // Devuelve el ID del usuario
  public int getId() {
        return id;
    }

 // Devuelve el nombre completo
    public String getNombreCompleto() {
        return nombreCompleto;
    }

// Devuelve el username
    public String getUsername() {
        return username;
    }

// Devuelve el rol del usuario
     public Rol getRol() {
        return rol;
    }

// MÉTODOS SET (MODIFICACIÓN)

// Permite cambiar el nombre del usuario
// Setters (para modificar datos)
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;

// Se registra la acción en el historial
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


    /**
 * Cambia el rol del usuario
 * Este método normalmente es usado por un administrador
 */
public void setRol(Rol nuevoRol) {
    this.rol = nuevoRol;
    registrarAccion("Cambio de rol a: " + nuevoRol);
}


 // SEGURIDAD / BLOQUEO

// Indica si la cuenta está bloqueada
public boolean estaBloqueado() {
    return bloqueado;
}


// Registra un intento fallido de inicio de sesión
public void registrarIntentoFallido() {
    intentosFallidos++;
    registrarAccion("Intento de inicio de sesión fallido");

    // Si falla 3 veces, se bloquea
    if (intentosFallidos >= 3) {
        bloqueado = true;
        registrarAccion("Cuenta bloqueada por múltiples intentos fallidos");
    }
}

// Reinicia los intentos cuando el login es exitoso
public void reiniciarIntentos() {
    intentosFallidos = 0;
}

// Desbloquea la cuenta (solo debe llamarlo un admin)
public void desbloquear() {
    bloqueado = false;
    intentosFallidos = 0;
    registrarAccion("Cuenta desbloqueada por un administrador");
}

 // HISTORIAL DE ACCIONES

// Registra una acción en el historial del usuario
    public void registrarAccion(String descripcion) {
        if (cantidadAcciones < historial.length) {
            historial[cantidadAcciones] = new Accion(descripcion);
            cantidadAcciones++;
        }
    }

 // Muestra todas las acciones realizadas por el usuario
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