package controller;

import entity.Paciente;
import model.ModelPaciente;

import javax.swing.*;
import java.time.LocalDate;

public class PacienteController {

    public static void create() {

        // Usamos el modelo
        ModelPaciente objModelPaciente = new ModelPaciente();

        // Pedimos los datos al Paciente
        String nombre = JOptionPane.showInputDialog("Enter the name of the patient");
        String apellidos = JOptionPane.showInputDialog("Enter the patient's last name");
        String fecha_nacimiento = JOptionPane.showInputDialog("Enter the patient's date of birth (AAAA-MM-DD)");
        String documento_paciente = JOptionPane.showInputDialog("Enter the patient's document number");

        // Creamos una instancia del paciente
        Paciente objPaciente = new Paciente();

        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFecha_nacimiento(LocalDate.parse(fecha_nacimiento));
        objPaciente.setDocumento_identidad(documento_paciente);

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
            String documento_identidad = JOptionPane.showInputDialog(null, "Enter the new identity document: " + objPaciente.getDocumento_identidad());

            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);

            objMPaciente.update(objPaciente);
        }
    }

    public static void getByNombre() {

        String idPaciente = JOptionPane.showInputDialog("Enter name patient");
        ModelPaciente objMPaciente = new ModelPaciente();

        String listaString = "COINCIDENCES \n";

        for (Paciente iterador : objMPaciente.findByName(idPaciente)) {
            listaString += iterador.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listaString);

    }

    public static void delete() {

        ModelPaciente objMPaciente = new ModelPaciente();

        String listMedico = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\n Enter the Id of the patient to be deleted: "));

        Paciente objPaciente = objMPaciente.findById(idDelete);

        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");

        } else {

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the Patient? \n" + objPaciente.toString());

            if (confirm == 0) objMPaciente.delete(objPaciente);
        }
    }

}
