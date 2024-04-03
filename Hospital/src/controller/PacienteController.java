package controller;

import entity.Medico;
import entity.Paciente;
import model.ModelPaciente;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class PacienteController {

    public static void create() {

        // Usamos el modelo
        ModelPaciente objModelPaciente = new ModelPaciente();

        // Pedimos los datos al ususario
        String nombre = JOptionPane.showInputDialog("Enter the name of the patient");
        String apellidos = JOptionPane.showInputDialog("Enter the patient's last name");
        String fecha_nacimiento = JOptionPane.showInputDialog("Enter the patient's date of birth (AAAA-MM-DD)");
        String documento_paciente = JOptionPane.showInputDialog("Enter the patient's document number");

        // Creamos una instancia del paciente
        Paciente objPaciente = new Paciente();

        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFecha_nacimiento(LocalDate.parse(fecha_nacimiento));

        // Llamamos el metodo de insercion
        objPaciente = (Paciente) objModelPaciente.insert(objPaciente);

        JOptionPane.showMessageDialog(null, objPaciente.toString());

    }

    public static void getAll() {

        ModelPaciente objMPaciente = new ModelPaciente();
        String listPaciente = "List of patients: \n";

        for (Object iterador : objMPaciente.findAll()) {

            //Convertir el objeto en paciente
            Paciente objPaciente = (Paciente) iterador;
            listPaciente += objPaciente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listPaciente);
    }

    public static String getAllString() {

        ModelPaciente objMPaciente = new ModelPaciente();
        String listPaciente = "List of patients: \n";

        for (Object iterador : objMPaciente.findAll()) {

            //Convertir el objeto en paciente
            Paciente objPaciente = (Paciente) iterador;
            listPaciente += objPaciente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listPaciente);
        return listPaciente;
    }

    public static void update() {

        // 1. Utilizamos el modelo
        ModelPaciente objMPaciente = new ModelPaciente();

        String listPaciente = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPaciente + "\n Enter the ID of the Patient  to edit: "));

        // Obteniendo un paciente por el id ingresado
        Paciente objPaciente = objMPaciente.findById(idUpdate);

        // Validamos que existe el medico
        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "Patient not found.");

        } else {

            String nombre = JOptionPane.showInputDialog(null, "Enter new name: " + objPaciente.getNombre());
            String apellidos = JOptionPane.showInputDialog(null, "Enter new last name(s): " + objPaciente.getApellidos());
            LocalDate fecha_nacimiento = LocalDate.parse(JOptionPane.showInputDialog(null, "Enter the new date of birth: " + objPaciente.getFecha_nacimiento()));
            String documento_identidad = JOptionPane.showInputDialog(null, "Enter the new identity document: " + objPaciente.getFecha_nacimiento());

            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);

            objMPaciente.update(objPaciente);
        }
    }









}
