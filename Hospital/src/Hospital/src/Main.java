import controller.EspecialidadController;
import controller.MedicoController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = "";

        do {
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

                            default:
                                JOptionPane.showMessageDialog(null, "Enter a valid option");
                        }

                    } while (!option1.equals("4"));
                    break;

                case "2":
                    String option2 = "";

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Enter a new doctor.
                                2. Consult all doctors.
                                3. Update doctor.
                                4. Delete a doctor
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
            }

        } while (!option.equals("5"));

        JOptionPane.showMessageDialog(null, "Chao :)");

    }
}