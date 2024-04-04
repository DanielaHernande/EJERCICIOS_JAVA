package controller;

import entity.Especialidad;
import model.ModelEspecialidad;

import javax.swing.*;

public class EspecialidadController {

    public static void create() {

        // Usamos el modelo
        ModelEspecialidad objModelEpecialidad = new ModelEspecialidad();

        // Pedimos los datos al usuario
        String nombre = JOptionPane.showInputDialog("Enter the name of the specialty");
        String descripcion = JOptionPane.showInputDialog("Enter a description of the specialty");

        // Creamos una instancia de especialidad
        Especialidad objEspecialidad = new Especialidad();

        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        // Llamamos el metodo de inserción
        objEspecialidad = (Especialidad) objModelEpecialidad.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());

    }

    public static void getAll() {

        ModelEspecialidad objModel = new ModelEspecialidad();
        String listEspecialidad = "Specialties list: \n";

        for (Object iterador : objModel.findAll()) {

            // Convertir el Objeto a una especialidad
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listEspecialidad);

    }

    public static String getAllString() {

        ModelEspecialidad objModel = new ModelEspecialidad();
        String listEspecialidad = "Specialties list: \n";

        for (Object iterador : objModel.findAll()) {

            // Convertir el Objeto a una especialidad
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        ;
        return listEspecialidad;
    }

    public static void delete() {

        ModelEspecialidad objMEspecialidad = new ModelEspecialidad();

        String listESpecialidad = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listESpecialidad + "\n Enter the Id of the specialty to be deleted: "));

        Especialidad objEspecialidad = objMEspecialidad.findById(idDelete);

        if (objEspecialidad == null) {
            JOptionPane.showMessageDialog(null, "Specialty not found");

        } else {

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the specialty? \n" + objEspecialidad.toString());

            if (confirm == 0) objMEspecialidad.delete(objEspecialidad);
        }
    }

}
