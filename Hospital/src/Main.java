import controller.CItaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = "";

        do {

            JOptionPane.showMessageDialog(null, "Welcome to the Hospital Management Program");
            option = JOptionPane.showInputDialog("""
                                        
                    1. Specialization option.
                    2. Add doctor.
                    3. Add a patient.
                    4. Add an appointment.
                    5. Exit.
                                        
                    Choose an option:
                    """);

            switch (option) {

                case "1":
                    String option1 = "";

                    do {
                        option1 = JOptionPane.showInputDialog("""
                                Specialty options menu
                                
                                1. Enter a new specialty.
                                2. Consult all specialties.
                                3. Eliminate a specialty.
                                4. Exit.
                                                                
                                Choose an option:
                                """);

                        switch (option1) {

                            case "1":
                                EspecialidadController.create();
                                break;

                            case "2":
                                EspecialidadController.getAll();
                                break;

                            case "3":
                                EspecialidadController.delete();
                                break;
                        }

                    } while (!option1.equals("4"));
                    break;

                case "2":
                    String option2 = "";

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                Menu of physician options
                                
                                1. Enter a new doctor.
                                2. Consult all doctors.
                                3. Update doctor.
                                4. Delete a doctor.
                                5. Exit.
                                                                
                                Choose an option:
                                """);
                        switch (option2) {

                            case "1":
                                MedicoController.create();
                                break;

                            case "2":
                                MedicoController.getAll();
                                break;

                            case "3":
                                MedicoController.update();
                                break;

                            case "4":
                                MedicoController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;

                case "3":
                    String option3 = "";

                    do {
                        option3 = JOptionPane.showInputDialog("""
                                Patient options menu
                                
                                1. Enter a new patient.
                                2. Consult patients.
                                3. Edit a patient.
                                4. Delete a patient.
                                5. Exit.
                                                                
                                Choose an option:
                                """);

                        switch (option3) {

                            case "1":
                                PacienteController.create();
                                break;

                            case "2":

                                String optionP = "";
                                do {
                                    optionP = JOptionPane.showInputDialog("""
                                            1. Consult all patients.
                                            2. Viewing a patient's name
                                            3. Exit.
                                                                            
                                            Choose an option:
                                            """);

                                    switch (optionP) {

                                        case "1":
                                            PacienteController.getAll();
                                            break;

                                        case "2":
                                            PacienteController.getByNombre();
                                            break;
                                    }
                                } while (!optionP.equals("3"));

                                break;

                            case "3":
                                PacienteController.update();
                                break;

                            case "4":
                                PacienteController.delete();
                                break;
                        }

                    } while (!option3.equals("5"));
                    break;

                case "4":

                    String option4 = "";

                    do {
                        option4 = JOptionPane.showInputDialog("""
                                Medical Appointment Options Menu
                                
                                1. Enter a new appointment.
                                2. Consult appointments by date.
                                3. Change the date of an appointment.
                                4. Delete an appointment.
                                5. Exit.
                                                                
                                Choose an option:
                                """);

                        switch (option4) {

                            case "1":
                                CItaController.create();
                                break;

                            case "2":
                                CItaController.getAll();
                                break;

                            case "3":
                                //CItaController.update();
                                break;

                            case "4":
                                CItaController.delete();
                                break;
                        }

                    } while (!option4.equals("5"));

                    break;
            }

        } while (!option.equals("5"));

        JOptionPane.showMessageDialog(null, "Chao :)");

    }
}