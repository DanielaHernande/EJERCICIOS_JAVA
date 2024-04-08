package controller;

import entity.Airplane;
import entity.Flight;
import model.ModelFlight;
import utils.Utils;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class FlightController {

    public static void insert() {

        String destination = JOptionPane.showInputDialog("Enter the destination");
        Date date_departure = Date.valueOf(JOptionPane.showInputDialog("Enter the departure date"));
        Time departure_time = Time.valueOf(JOptionPane.showInputDialog("Enter the departure time"));

        Object[] optionsFlight = Utils.listToArray(AirplaneController.instanceModel().findAll());


        Airplane objAirplane = (Airplane) JOptionPane.showInputDialog(null,
                "Select an airplane:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFlight,
                optionsFlight[0]
        );

        instanceModel().insert(new Flight(destination, date_departure, departure_time, objAirplane.getId(), objAirplane));
    }

    public static void update() {

        Object[] options = Utils.listToArray(FlightController.instanceModel().findAll());

        Flight objFlight = (Flight) JOptionPane.showInputDialog(null,
                "Select the flight you want to update:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String destination = JOptionPane.showInputDialog(null, "Enter the destination", objFlight.getDestination());
        Date date_departure = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the departure date", objFlight.getDate_departure()));
        Time departure_time = Time.valueOf(JOptionPane.showInputDialog(null, "Enter the departure time", objFlight.getDeparture_time()));

        Object[] optionsFlight = Utils.listToArray(AirplaneController.instanceModel().findAll());

        Airplane objAirplane = (Airplane) JOptionPane.showInputDialog(null,
                "Select an airplane:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFlight,
                optionsFlight[0]
        );

        instanceModel().update(new Flight(destination, date_departure, departure_time, objAirplane.getId(), objAirplane));
    }

    public static void delete() {

        Object[] options = Utils.listToArray(FlightController.instanceModel().findAll());

        Flight objFlight = (Flight) JOptionPane.showInputDialog(null,
                "Select a flight to delete:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objFlight);

    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {

        String listString = "List of flights";

        for (Object temp : list) {

            Flight objFlight = (Flight) temp;
            listString += objFlight.toString() + "\n";
        }

        return listString;
    }

    public static void getByDestination() {

        String destination = JOptionPane.showInputDialog("destino");

        String list = getAll(instanceModel().findAllDestination(destination));

        JOptionPane.showMessageDialog(null, list);

    }

    public static ModelFlight instanceModel() {
        return new ModelFlight();
    }
}
