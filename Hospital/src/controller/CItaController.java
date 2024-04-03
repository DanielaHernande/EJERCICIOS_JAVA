package controller;

import entity.Cita;
import model.ModelCita;
import model.ModelMedico;
import model.ModelPaciente;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;


public class CItaController {

    public static void create() {

        // Usamos el modelo
        ModelCita objMCita = new ModelCita();
        ModelMedico objMedico = new ModelMedico();
        ModelPaciente objPaciente = new ModelPaciente();

        // Pedimos los datos al ususario
        int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(objPaciente.findAll() +"Enter the patient ID: "));
        int id_medico = Integer.parseInt(JOptionPane.showInputDialog(objMedico.findAll() + "Enter the Physician ID: "));
        Date fecha_cita = Date.valueOf(JOptionPane.showInputDialog("Enter the date of the appointment (YYYYY-MM-DD)"));
        Time hora_cita = Time.valueOf(JOptionPane.showInputDialog("Enter the appointment time (HH:MM:SS)"));
        String motivo = JOptionPane.showInputDialog("Enter the reason for the appointment");

        // Creamos una instancia de médico
        Cita objCita = new Cita();

        objCita.setId_paciente(id_paciente);
        objCita.setId_medico(id_medico);
        objCita.setFecha_cita(fecha_cita);
        objCita.setHora_cita(hora_cita);
        objCita.setMotivo(motivo);

        // Llamamos el metodo de insercion
        objCita = (Cita) objMCita.insert(objCita);

        JOptionPane.showMessageDialog(null, objCita.toString());

    }

/*    public static void getAll() {

        ModelCita objModelCita = new ModelCita();
        String listCita = "Appointment list \n";

        for (Object iterador : objModelCita.findByDate()) {

            //Convertir el objeto en medico
            Cita objCita = (Cita) iterador;
            listCita += objCita.toString() + "\n --------------------------------------";
        }

        JOptionPane.showMessageDialog(null, listCita);
    }*/

}
