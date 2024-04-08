package controller;

import entity.Airplane;
import entity.Flight;
import entity.Passenger;
import model.ModelPassenger;
import utils.Utils;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PassengerController {

    public static void insert() {

        String name = JOptionPane.showInputDialog("Enter the passenger's name: ");
        String last_name = JOptionPane.showInputDialog("Enter the passenger's last name:");
        String identity_document = JOptionPane.showInputDialog("Enter the passenger's ID document");

        instanceModel().insert(new Passenger(name, last_name, identity_document));

    }

    public static void update() {

        Object[] options = Utils.listToArray(PassengerController.instanceModel().findAll());

        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(null,
                "Select the passenger you wish to update:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String name = JOptionPane.showInputDialog(null, "Enter the name of passenger", objPassenger.getName());
        String last_name = JOptionPane.showInputDialog(null, "Enter the passenger's last name", objPassenger.getLast_name());
        String document = JOptionPane.showInputDialog(null, "Enter the passenger's identity document", objPassenger.getIdentity_document());

        instanceModel().update(new Passenger(name, last_name, document));
    }

    public static void delete() {

        Object[] options = Utils.listToArray(PassengerController.instanceModel().findAll());

        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(null,
                "Select the passenger to be deleted::",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objPassenger);

    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {

        String listString = "Passenger List";

        for (Object temp : list) {

            Passenger objPassenger = (Passenger) temp;
            listString += objPassenger.toString() + "\n";
        }

        return listString;
    }

    public static void getByName() {

        String name = JOptionPane.showInputDialog("Nombre del pasajero");

        String list = getAll(instanceModel().findAllName(name));

        JOptionPane.showMessageDialog(null, list);

    }

    public static ModelPassenger instanceModel() {
        return new ModelPassenger();
    }

}
