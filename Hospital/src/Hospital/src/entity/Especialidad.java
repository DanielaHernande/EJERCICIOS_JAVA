package entity;

public class Especialidad {

    private int id_especialidad;
    private String nombre;
    private String descripcion;

    // Constructores

    // Vacío
    public Especialidad() {
    }

    // Con parámetros
    public Especialidad(int id_especialidad, String nombre, String descripcion) {
        this.id_especialidad = id_especialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Métodos getters and setters
    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
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
        return "Especialidad " +
                "id_especialidad=" + id_especialidad +
                ", Nombre='" + nombre + '\'' +
                ", Descripcion= " + descripcion ;
    }
}
