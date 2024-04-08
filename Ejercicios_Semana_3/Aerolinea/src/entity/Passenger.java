package entity;

public class Passenger {

    private int id;
    private String name;
    private String last_name;
    private String identity_document;

    // Constructores
    public Passenger() {
    }

    public Passenger(String name, String last_name, String identity_document) {
        this.name = name;
        this.last_name = last_name;
        this.identity_document = identity_document;
    }

    // Metodos getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentity_document() {
        return identity_document;
    }

    public void setIdentity_document(String identity_document) {
        this.identity_document = identity_document;
    }

    // toString

    @Override
    public String toString() {
        return "\nPassenger: " +
                " Name= " + name + " " + last_name + '\'' +
                "  Document= " + identity_document;
    }
}
