public class EmpleadoPermanente extends Empleado {

    // Atributo
    private String departamento;

    // Contructor
    public EmpleadoPermanente(String nombre, int edad, String idEmpleado, double salario, String departamento) {
        super(nombre, edad, idEmpleado, salario);
        this.departamento = departamento;
    }

    // Metodos getter and setter

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "EmpleadoPermanente{" +
                "departamento='" + departamento + '\'' +
                '}';
    }
}
