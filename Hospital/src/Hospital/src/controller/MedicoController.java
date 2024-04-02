package controller;

import entity.Especialidad;
import entity.Medico;
import model.ModelMedico;
import model.ModelEspecialidad;

import javax.swing.*;


public class MedicoController {

    public static void create() {

        // Usamos el modelo
        ModelMedico objModelMedico = new ModelMedico();
        ModelEspecialidad objMOdel = new ModelEspecialidad();

        // Pedimos los datos al ususario
        String nombre = JOptionPane.showInputDialog("Enter the name of the doctor");
        String apellidos = JOptionPane.showInputDialog("Enter the doctor's last name(s)");
        int especialidad = Integer.parseInt(JOptionPane.showInputDialog(objMOdel.findAll() + "\n" + "Enter the specialty ID"));

        // Creamos una instancia de médico
        Medico objMedico = new Medico();

        objMedico.setNombre(nombre);
        objMedico.setApellidos(apellidos);
        objMedico.setId_especialidad(especialidad);

        // Llamamos el metodo de insercion
        objMedico = (Medico) objModelMedico.insert(objMedico);

        JOptionPane.showMessageDialog(null, objMedico.toString());

    }

    public static void getAll() {

        ModelMedico objModelM = new ModelMedico();
        String listMedico = "List of doctors \n";

        for (Object iterador : objModelM.findAll()) {

            //Convertir el objeto en medico
            Medico objMedico = (Medico) iterador;
            listMedico += objMedico.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listMedico);
    }

    public static String getAllString() {

        ModelMedico objModel = new ModelMedico();
        String listMedico = "List of doctors: \n";

        for (Object iterador : objModel.findAll()) {

            // Convertir el Objeto a una especialidad
            Medico objMedico = (Medico) iterador;
            listMedico += objMedico.toString() + "\n";
        }
        ;
        return listMedico;
    }

    public static void update() {

        // 1. Utilizamos el modelo
        ModelMedico objModelMedico = new ModelMedico();

        String listMedico = getAllString();
        String listEspecialidad = EspecialidadController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\n Enter the ID of the doctor to edit: "));

        // Obteniendo un medico por el id ingresado
        Medico objMedico = objModelMedico.findById(idUpdate);

        // Validamos que existe el medico
        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "Doctor not found.");

        } else {

            int especialidad = Integer.parseInt(JOptionPane.showInputDialog(null, listEspecialidad + "Enter the new specialty: ", +objMedico.getId_especialidad()));

            objMedico.setId_especialidad(especialidad);

            objModelMedico.update(objMedico);
        }
    }

    public static void delete() {

        ModelMedico objModelMedico = new ModelMedico();

        String listMedico = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\n Enter the Id of the doctor to be deleted: "));

        Medico objMedico = objModelMedico.findById(idDelete);

        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "Doctor not found");

        } else {

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the doctor? \n" + objMedico.toString());

            if (confirm == 0) objModelMedico.delete(objMedico);
        }
    }
}
