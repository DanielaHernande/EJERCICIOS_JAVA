package controller;

import entity.Paciente;
import model.ModelPaciente;

import javax.swing.*;
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
        objPaciente.setFecha_nacimiento(fecha_nacimiento);

        // Llamamos el metodo de insercion
        objPaciente = (Paciente) objModelPaciente.insert(objPaciente);

        JOptionPane.showMessageDialog(null, objPaciente.toString());

    }



}
