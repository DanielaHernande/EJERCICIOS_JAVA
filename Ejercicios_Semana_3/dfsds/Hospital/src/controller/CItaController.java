package controller;

import entity.Cita;
import model.ModelCita;
import model.ModelMedico;
import model.ModelPaciente;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class CItaController {

    public static void create() {

        // Usamos el modelo
        ModelCita objMCita = new ModelCita();
        ModelMedico objMedico = new ModelMedico();
        ModelPaciente objPaciente = new ModelPaciente();

        // Pedimos los datos al Cita
        int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(objPaciente.findAll() +"\n Enter the patient ID: "));
        int id_medico = Integer.parseInt(JOptionPane.showInputDialog(objMedico.findAll() + "Enter the Physician ID: "));
        Date fecha_cita = Date.valueOf(JOptionPane.showInputDialog("Enter the date of the appointment (YYYYY-MM-DD)"));
        Time hora_cita = Time.valueOf(JOptionPane.showInputDialog("Enter the appointment time (HH:MM:SS)"));
        String motivo = JOptionPane.showInputDialog("Enter the reason for the appointment");

        // Creamos una instancia de cita
        Cita objCita = new Cita();

        objCita.setId_paciente(id_paciente);
        objCita.setId_medico(id_medico);
        objCita.setFecha_cita(fecha_cita);
        objCita.setHora_cita(hora_cita);
        objCita.setMotivo(motivo);

        // Llamamos el metodo de insercion
        //objCita = (Cita) objMCita.insert(objCita);
        objMCita.insert(objCita);



    }

    public static void getAll() {

        String fecha_cita = JOptionPane.showInputDialog("Enter the date of the appointment (YYYY-MM-DD):");

        Date fechaCita = Date.valueOf(fecha_cita);

        ModelCita objMCita = new ModelCita();
        String listCita = "Appointment list: " + fechaCita + " \n";

        List<Cita> citas = objMCita.findByDate(fechaCita);
        System.out.println(citas);
        for (Cita cita : citas) {
            listCita += cita.toString() + "\n";
        }


        JOptionPane.showMessageDialog(null, listCita);
    }

    public static String getAllString() {

        ModelCita objModel = new ModelCita();
        String listCita = "Appointment list: \n";

        for (Object iterador : objModel.findAll()) {

            // Convertir el Objeto a una especialidad
            Cita objCita = (Cita) iterador;
            listCita += objCita.toString() + "\n";
        }

        return listCita;
    }

    public static void update() {

        // 1. Utilizamos el modelo
        ModelCita objMCita = new ModelCita();

        String listCita = getAllString();
        String fecha_cita = JOptionPane.showInputDialog("Enter the date of the appointment (YYYY-MM-DD):");

        Date fechaCita = Date.valueOf(fecha_cita);

         int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCita + "\n Enter the ID of the appointment to edit: "));

        // Obteniendo una cita por el id ingresado
        Cita objCita = objMCita.findById(idUpdate);

        // Validamos que existe la cita
        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "appointment not found.");

        } else {



            objCita.setFecha_cita(fechaCita);

            objMCita.update(objCita);
        }
    }

    public static void delete() {

        ModelCita objMCita = new ModelCita();

        String listCita = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCita + "\n Enter the Id of the Appointment to be deleted: "));

        Cita objCita = objMCita.findById(idDelete);

        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "Appointment not found");

        } else {

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the Appointment? \n" + objCita.toString());

            if (confirm == 0) objMCita.delete(objCita);
        }
    }

}
