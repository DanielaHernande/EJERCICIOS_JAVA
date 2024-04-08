package controller;

import entity.Airplane;
import entity.Flight;
import entity.Passenger;
import entity.Reservation;
import model.ModelReservation;
import utils.Utils;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReservationController {

    public static void insert() {

        Object[] optionsPassengers = Utils.listToArray(PassengerController.instanceModel().findAll());
        Object[] optionsFlight = Utils.listToArray(FlightController.instanceModel().findAll());

        Passenger passengerSelct = (Passenger) JOptionPane.showInputDialog(null,
                "Select passenger:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPassengers,
                optionsPassengers[0]
        );

        Flight flightSelct = (Flight) JOptionPane.showInputDialog(null,
                "Select flight:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFlight,
                optionsFlight[0]
        );

        Date reservation_date = Date.valueOf(JOptionPane.showInputDialog("Enter the date of the reservation (YYYY-MM-DD):"));
        String seat = JOptionPane.showInputDialog("Enter the airplane seat:");

        instanceModel().insert(new Reservation(passengerSelct.getId(), flightSelct.getId(), reservation_date, seat, passengerSelct, flightSelct));

    }

    public static void update() {

        Object[] options = Utils.listToArray(ReservationController.instanceModel().findAll());

        Reservation objReservation = (Reservation) JOptionPane.showInputDialog(null,
                "Select the reservation you want to update:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );


        Object[] optionsPasseger = Utils.listToArray(PassengerController.instanceModel().findAll());

        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(null,
                "Select an passeger:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPasseger,
                optionsPasseger[0]
        );

        Object[] optionFlight = Utils.listToArray(FlightController.instanceModel().findAll());

        Flight objFlight = (Flight) JOptionPane.showInputDialog(null,
                "Select an flight:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionFlight,
                optionFlight[0]
        );

        Date reservation_date = Date.valueOf(JOptionPane.showInputDialog("Enter a new date", objReservation.getReservation_date()));
        String seat = JOptionPane.showInputDialog("Enter a new seat", objReservation.getSeat());


        instanceModel().update(new Reservation(objPassenger.getId(), objFlight.getId(), reservation_date, seat, objPassenger, objFlight));
    }

    public static void delete() {

        Object[] options = Utils.listToArray(ReservationController.instanceModel().findAll());

        Reservation objReservation = (Reservation) JOptionPane.showInputDialog(null,
                "Select a reservation to delete:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objReservation);

    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {

        String listString = "Reservation list";

        for (Object temp : list) {

            Reservation objReservations = (Reservation) temp;
            listString += objReservations.toString() + "\n";
        }

        return listString;
    }

    public static void getByVuelo() {

        String name = JOptionPane.showInputDialog("Enter a destination");

        String list = getAll(instanceModel().findAllVuelo(name));

        JOptionPane.showMessageDialog(null, list);

    }

    public static ModelReservation instanceModel() {
        return new ModelReservation();
    }
}
