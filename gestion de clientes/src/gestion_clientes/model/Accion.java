package gestion_clientes.model;

/**
 * Representa una acción realizada por un usuario
 * Clase Accion
 * Representa una acción realizada por un usuario dentro del sistema.
 * Cada acción tiene:
 * - Una descripción (qué hizo el usuario)
 * - Un momento en el tiempo (timestamp)
 */
public class Accion {

    // ATRIBUTOS

    // Texto que describe la acción realizada
    private String descripcion;  // qué hizo el usuario

    // Marca de tiempo en milisegundos (cuándo ocurrió la acción)
    private long timestamp;      //cuándo lo hizo (milisegundos)


    /**
     * Constructor de la acción
     * Se ejecuta cuando se crea una nueva acción
     */
    public Accion(String descripcion) {
        this.descripcion = descripcion;
        // Se guarda el momento exacto en que ocurrió la acción
        this.timestamp = System.currentTimeMillis();  //cumple el requisito
    }

      // MÉTODOS GET (LECTURA)

    // Devuelve la descripción de la acción
    public String getDescripcion() {
        return descripcion;
    }

     // Devuelve el momento en que ocurrió la acción
    public long getTimestamp() {
        return timestamp;
    }
    
}
