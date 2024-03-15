import javax.swing.*;
import java.util.ArrayList;

public class GestionEmpleados {

    // Atributo
    private ArrayList<Empleado> empleados;

    public GestionEmpleados() {
        this.empleados = new ArrayList<>();
    }

    // Metodo Agregar empleado
    public void agregarEmpleado() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado que desea agregar: ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del empleado: "));

        String idEmpleado = "";

        // Variable bandera para saber si el id del empleado ya existe
        boolean idUnico = false;

        do {
            idEmpleado = JOptionPane.showInputDialog("Ingrese el id del empleado: ");

            // Verificar si el idEmpleado ya existe
            if (buscarIdEmpleado(idEmpleado) != null) {
                JOptionPane.showMessageDialog(null, "El Id ingreasado ya esta en uso.");

            } else {
                // Si no existe el bucle do - while se detendra
                idUnico = true;
            }

            // Si es diferente a false sale del bucle
        } while (!idUnico);


        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario del empleado: "));

        JOptionPane.showMessageDialog(null, "Empleado agregado exitosamente.");

        // Creamos un nuevo objeto
        Empleado empleado = new Empleado(nombre, edad, idEmpleado, salario);
        this.empleados.add(empleado);
    }

    // Metodo eliminar
    public boolean eliminarEmpleado(String idEmpleado) {
        return this.empleados.removeIf(empleados -> empleados.getIdEmpleado().equals(idEmpleado));
    }

    // Metodo mostrar empleados
    public void mostrarEmpleados() {

        String listaEmpleados = "Lista de empleados: \n";

        // foreach
        for (Empleado empleado : empleados) {
            listaEmpleados += "\nEl id del empleado es: " + empleado.getIdEmpleado() + "\n" +
                    "El nombre del empleado es: " + empleado.getNombre() + "\n" +
                    "El salario del empleado es: " + empleado.getSalario() + "\n" +
                    "" +
                    "--------------------------------------------------------------------------";
        }
        JOptionPane.showMessageDialog(null, listaEmpleados);
    }

    public Empleado buscarIdEmpleado(String buscarId) {

        for (Empleado temporal : this.empleados) {
            if (temporal.getIdEmpleado().equalsIgnoreCase(buscarId)) {
                return temporal;
            }
        }
        return null;
    }

}
