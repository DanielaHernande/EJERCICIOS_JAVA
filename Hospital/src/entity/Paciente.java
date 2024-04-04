package entity;

import java.time.LocalDate;
import java.util.Date;

public class Paciente {

    private int id_paciente;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private String documento_identidad;

    // Constructores
    // Vacío
    public Paciente() {
    }

    // Con parámetros
    public Paciente(int id_paciente, String nombre, String apellidos, LocalDate fecha_nacimiento, String documento_identidad) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.documento_identidad = documento_identidad;
    }

    // Métodos getters and setters
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    // toString
    @Override
    public String toString() {
        return "\nPaciente " + "\n" +
                " id_paciente= " + id_paciente + "\n" +
                " Nombre= " + nombre + " " + apellidos + '\n' +
                " Fecha de nacimiento= " + fecha_nacimiento + "\n" +
                " Documento de identidad= " + documento_identidad + "\n" +
                "------------------------------------------------";
    }
}
