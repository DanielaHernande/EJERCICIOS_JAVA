package entity;

public class Airplane {

    // Atributos
    private int id;
    private String model;
    private int capacity;

    // Constructor vacío
    public Airplane() {
    }

    // Constructor con parámetros
    public Airplane(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    // Métodos getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // toString
    @Override
    public String toString() {
        return "Airplane: " + "\n" +
                "  Id= " + id + "\n" +
                "  Model= " + model + "\n" +
                "  Capacity=" + capacity + "\n" + "\n";
    }
}
