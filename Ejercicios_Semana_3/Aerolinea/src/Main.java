import controller.AirplaneController;
import database.ConfigDB;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String option = "", option1 = "";

        do {

            option = JOptionPane.showInputDialog("""
                                        
                    1. Aircraft options.
                    2. Add doctor.
                    3. Add a patient.
                    4. Add an appointment.
                    5. Exit.
                                        
                    Choose an option:
                    """);

            switch (option) {

                case "1":

                    do {

                        option1 = JOptionPane.showInputDialog("""    
                                                    
                                1. Aircraft options.
                                2. Eliminate an airplane.
                                3. Exit.
                                                      
                                Choose an option:
                                """);

                        switch (option1) {

                            case "1":
                                AirplaneController.insert();
                                break;

                            case "2":
                                AirplaneController.delete();

                                break;
                        }

                    } while (!option1.equals("3"));
                    break;
            }

        } while (!option.equals("5"));


    }
}