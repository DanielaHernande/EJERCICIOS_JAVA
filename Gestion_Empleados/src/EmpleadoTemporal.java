public class EmpleadoTemporal extends Empleado{

    // Atributos
    private String fechaFinContrato;

    // Contructor
    public EmpleadoTemporal(String nombre, int edad, String idEmpleado, double salario, String fechaFinContrato) {
        super(nombre, edad, idEmpleado, salario);
        this.fechaFinContrato = fechaFinContrato;
    }

    // Metodos getter and setter

    public String getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(String fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public String toString() {
        return "EmpleadoTemporal{" +
                "fechaFinContrato='" + fechaFinContrato + '\'' +
                '}';
    }
}
