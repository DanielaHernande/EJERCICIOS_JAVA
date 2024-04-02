import controller.EspecialidadController;
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
                    break;
            }

        } while (!option.equals("5"));

        JOptionPane.showMessageDialog(null, "Chao :)");

    }
}