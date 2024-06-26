package entity;

public class Medico {

    private int id_medico;
    private String nombre;
    private String apellidos;
    private int id_especialidad;

    private Especialidad especialidad;

    // Constructores
    // Vacío
    public Medico() {
    }

    // Con parámetros
    public Medico(int id_medico, String nombre, String apellidos, int id_especialidad) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_especialidad = id_especialidad;
    }

    // Métodos getters and setters
    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    // toString
    @Override
    public String toString() {
        return "\nMedico: " + "\n" +
                " id_medico=" + id_medico + "\n" +
                " Dr(a) = " + nombre + " " + apellidos + '\n' +
                " id_especialidad= " + especialidad + "\n" +
                "----------------------------------------------";
    }

}
