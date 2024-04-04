package entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Cita {

    private int id_cita;
    private int id_paciente;
    private int id_medico;
    private Date fecha_cita;
    private Time hora_cita;
    private String motivo;

    private Medico medico;

    private Paciente paciente;

    // Constructores

    public Cita() {
    }

    public Cita(int id_cita, int id_paciente, int id_medico, Date fecha_cita, Time hora_cita, String motivo, Medico medico, Paciente paciente) {
        this.id_cita = id_cita;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public Time getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(Time hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    // Vacío

    @Override
    public String toString() {
        return "Cita " +
                "id_cita=" + id_cita +
                ", id_paciente=" + id_paciente +
                ", id_medico=" + id_medico +
                ", fecha_cita=" + fecha_cita +
                ", hora_cita=" + hora_cita +
                ", motivo='" + motivo + '\'' +
                ", medico=" + medico.getNombre() +
                ", paciente=" + paciente.getNombre() +
                '}';
    }
}
