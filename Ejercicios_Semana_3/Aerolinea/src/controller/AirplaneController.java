package controller;

import entity.Airplane;
import model.ModelAirplane;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AirplaneController {

    public static void insert() {

        String model = JOptionPane.showInputDialog("Enter the aircraft model");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the aircraft capacity"));

        instanceModel().insert(new Airplane(model, capacity));
    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> List) {

        String listAirplane = "list of airplane \n";

        for (Object iterador : List) {

            // COnvertir el objeto a un avion
            Airplane objAirplane = (Airplane) iterador;
            listAirplane += objAirplane.toString() + "\n";
        }

        return listAirplane;
    }

    public static void delete() {

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Airplane objSelected = (Airplane) JOptionPane.showInputDialog(
                null,
                "Select an aircraft to delete: ",
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
