import javax.swing.*;

public class Main {
    public static void main(String[] args) {
/*      Ejercicio 2: Sistema de Registro de Empleados
        Objetivo: Desarrollar un sistema para manejar los empleados de una empresa, aplicando
        encapsulamiento, herencia y polimorfismo, y utilizando ArrayList para almacenar los
        empleados.

        Principios de POO aplicados: Encapsulamiento, Herencia, Polimorfismo.

        Requisitos:

        Clase Persona: Con propiedades básicas como nombre y edad.
        Clase Empleado: Hereda de Persona y añade propiedades como idEmpleado y salario. Usa
        encapsulamiento adecuadamente.
        Clase EmpleadoTemporal y Clase EmpleadoPermanente: Heredan de Empleado y
        representan diferentes tipos de empleados.
        Clase GestionEmpleados: Utiliza un ArrayList para gestionar objetos del tipo Empleado.
        Implementa métodos para añadir, eliminar y mostrar empleados. Utiliza polimorfismo para
        manejar diferentes tipos de empleados.
        */
        String option;
        GestionEmpleados gestionEmpleado = new GestionEmpleados();

        do {
            option = JOptionPane.showInputDialog("MENU DE OPCIONES\n" +
                    "1. Para agregar un nuevo empleado. \n" +
                    "2. Para mostrar los empleados. \n" +
                    "3. Para eliminar algun empleado. \n" +
                    "4. Salir del programa \n\n" +

                    "Ingrese una opcion:");

            switch (option) {

                case "1":
                    gestionEmpleado.agregarEmpleado();
                    break;

                case "2":
                    gestionEmpleado.mostrarEmpleados();
                    break;

                case "3":

                    String codigoELiminar = "";

                    // Mostrar los empleados
                    gestionEmpleado.mostrarEmpleados();

                    codigoELiminar = JOptionPane.showInputDialog("Ingrese el id del empleado que desa eliminar: ");

                    boolean objempleado = gestionEmpleado.eliminarEmpleado(codigoELiminar);

                    if (!objempleado) {

                        JOptionPane.showMessageDialog(null, "El codigo ingresado no corresponde a ningun empleado.");

                    } else {
                        JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente :).");
                    }

                    break;
            }

        } while (!option.equals("4"));
        JOptionPane.showMessageDialog(null, "Hasta la proxima :) .");


    }
}