package gutierrezruiz.francisco;

/**
 * @author Francisco Gutiérrez Ruiz
 * @version 1.0
 * @since 2024/10/16
 * <p>
 * Personaje.
 */
public class Personaje {
    private final String nombre; // Nombre del personaje
    private final int imagenId; // Imagen del personaje. Es un entero
    private final String descripcion; // La descripción del personaje
    private final String habilidad; // La habilidad del personaje

    /**
     * Instancia un nuevo personaje.
     *
     * @param nombre      el nombre
     * @param imagenId    el id de la imagen
     * @param descripcion la descripción
     * @param habilidad   la habilidad
     */

    // Constructor
    public Personaje(String nombre, int imagenId, String descripcion, String habilidad) {
        this.nombre = nombre;
        this.imagenId = imagenId;
        this.descripcion = descripcion;
        this.habilidad = habilidad;
    }

    /**
     * Devuelve el nombre del personaje.
     *
     * @return el nombre del personaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el id de la imagen del personaje.
     *
     * @return el id de la imagen del personaje
     */
    public int getImagenId() {
        return imagenId;
    }

    /**
     * Devuelve la descripcion del personaje.
     *
     * @return la descripción del personaje
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve la habilidad del personaje.
     *
     * @return la habilidad del personaje
     */
    public String getHabilidad() {
        return habilidad;
    }
} // Fin class