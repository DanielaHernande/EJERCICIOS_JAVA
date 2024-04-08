import controller.AirplaneController;
import controller.FlightController;
import controller.PassengerController;
import controller.ReservationController;
import database.ConfigDB;
import entity.Reservation;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option = "", option1 = "", option2 = "", option3 = "", option4 = "";

        do {

            option = JOptionPane.showInputDialog("""
                                        
                    1. Aircraft options.
                    2. Flight options.
                    3. Passenger options.
                    4. Flight reservation options.
                    5. Exit.
                                        
                    Choose an option:
                    """);

            switch (option) {

                case "1":

                    do {

                        option1 = JOptionPane.showInputDialog("""    
                                                    
                                1. Add an airplane.
                                2. Eliminate an airplane.
                                3. List all airplanes
                                4. Exit.
                                                      
                                Choose an option:
                                """);

                        switch (option1) {

                            case "1":
                                AirplaneController.insert();
                                break;

                            case "2":
                                AirplaneController.delete();
                                break;

                            case "3":
                                AirplaneController.getAll();
                                break;
                        }

                    } while (!option1.equals("4"));
                    break;

                case "2":

                    do {

                        option2 = JOptionPane.showInputDialog("""    
                                                    
                                1. Add a flight.
                                2. List all flights.
                                3. Search flight by destination.
                                4. Update flight date.
                                5. Delete a flight.
                                6. Exit.
                                                      
                                Choose an option:
                                """);

                        switch (option2) {

                            case "1":
                                FlightController.insert();
                                break;

                            case "2":
                                FlightController.getAll();
                                break;

                            case "3":
                                FlightController.getByDestination();
                                break;

                            case "4":
                                FlightController.update();
                                break;

                            case "5":
                                FlightController.delete();
                                break;
                        }


                    } while (!option2.equals("6"));

                    break;

                case "3":

                    do {

                        option3 = JOptionPane.showInputDialog("""    
                                                    
                                1. Add a passenger.
                                2. Passenger List.
                                3. Search for a passenger by name.
                                4. Update flight date.
                                5. Delete a flight.
                                6. Exit.
                                                      
                                Choose an option:
                                """);

                        switch (option3) {

                            case "1":
                                PassengerController.insert();
                                break;

                            case "2":
                                PassengerController.getAll();
                                break;

                            case "3":
                                PassengerController.getByName();
                                break;

                            case "4":
                                PassengerController.update();
                                break;

                            case "5":
                                PassengerController.delete();
                                break;
                        }

                    } while (!option3.equals("6"));

                    break;

                case "4":

                    do {

                        option4 = JOptionPane.showInputDialog("""    
                                                    
                                1. Enter a reservation.
                                2. List of all reservations.
                                3. Search reservations by flight.
                                4. Updating a flight seat.
                                5. Delete a reservation.
                                6. Exit.
                                                      
                                Choose an option:
                                """);

                        switch (option4) {

                            case "1":
                                ReservationController.insert();
                                break;

                            case "2":
                                ReservationController.getAll();
                                break;

                            case "3":
                                ReservationController.getByVuelo();
                                break;

                            case "4":
                                ReservationController.update();
                                break;

                            case "5":
                                ReservationController.delete();
                                break;

                        }

                    } while (!option4.equals("6"));

                    break;
            }

        } while (!option.equals("5"));

        JOptionPane.showMessageDialog(null, "Chao :)");

    }
}