package entity;

public class Author {

    private int id;
    private String name;
    private String nacionality;

    // Constructor vacío
    public Author() {
    }

    // Constructor con parametros
    public Author(int id, String name, String nacionality) {
        this.id = id;
        this.name = name;
        this.nacionality = nacionality;
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

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    // toString
    @Override
    public String toString() {
        return "Author " +
                "id =" + id +
                ", name = " + name + '\'' +
                ", nacionality = " + nacionality;
    }
}
