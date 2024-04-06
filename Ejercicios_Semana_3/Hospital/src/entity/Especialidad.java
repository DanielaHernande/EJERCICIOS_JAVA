package entity;

public class Especialidad {

    private int id;
    private String nombre;
    private String descripcion;

    // Constructores

    // Vacío
    public Especialidad() {
    }

    // Con parámetros
    public Especialidad(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Métodos getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // toString
    @Override
    public String toString() {

        return "\n" +
                "Id = " + id + "\n" +
                "Nombre= " + nombre + "\n" +
                "Descripcion= " + descripcion + "\n" +
                "---------------------------------------------------------";
    }
}
