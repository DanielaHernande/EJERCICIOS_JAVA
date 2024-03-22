package entity;

public class Book {

    // Atributos
    private int id;
    private String title;
    private int yearPublication;
    private double price;
    private int id_author;

    // Constructor vacio
    public Book() {
    }

    // Constructor con parametros
    public Book(int id, String title, int yearPublication, double price, int id_author) {
        this.id = id;
        this.title = title;
        this.yearPublication = yearPublication;
        this.price = price;
        this.id_author = id_author;
    }

    // Metodos getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    // toString
    @Override
    public String toString() {
        return "Book " +
                "id = " + id +
                ", title =" + title + '\'' +
                ", yearPublication = " + yearPublication +
                ", price = " + price +
                ", id_author =" + id_author;
    }
}
