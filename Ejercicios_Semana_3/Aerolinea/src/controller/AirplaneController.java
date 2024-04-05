package controller;

import entity.Airplane;
import model.ModelAirplane;
import utils.Utils;

import javax.swing.*;

public class AirplaneController {

    public static void insert() {

        String model = JOptionPane.showInputDialog("Ingrese el modelo");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("ingrese la capacidad"));

        instanceModel().insert(new Airplane(model, capacity));
    }

    public static void getAll() {

        ModelAirplane objModelAitplane = new ModelAirplane();
        String listAirplane = "list of airplane \n";

        for (Object iterador : objModelAitplane.findAll()) {

            // COnvertir el objeto a un avion
            Airplane objAirplane = (Airplane) iterador;
            listAirplane += objAirplane.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAirplane);
    }

    public static String getAllString() {

        ModelAirplane objModelAitplane = new ModelAirplane();
        String listAirplane = "list of airplane \n";

        for (Object iterador : objModelAitplane.findAll()) {

            // COnvertir el objeto a un avion
            Airplane objAirplane = (Airplane) iterador;
            listAirplane += objAirplane.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAirplane);
        return listAirplane;
    }

    public static void delete() {

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Airplane objSelected = (Airplane) JOptionPane.showInputDialog(
                null,
                "Selecciona un avion ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objSelected);
    }

    public static ModelAirplane instanceModel() {
        return new ModelAirplane();
    }

}
