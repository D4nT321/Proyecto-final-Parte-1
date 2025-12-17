package gestion_clientes.model;

/**
 * Representa una acción realizada por un usuario
 */
public class Accion {

    private String descripcion;  // qué hizo el usuario
    private long timestamp;      //cuándo lo hizo (milisegundos)

    public Accion(String descripcion) {
        this.descripcion = descripcion;
        this.timestamp = System.currentTimeMillis();  //cumple el requisito
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
}
