package controller;

import database.ConfigDB;
import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {

    public static void create() {

        // Usamos el modelo del libro
        BookModel objBookModel = new BookModel();

        // Pedimos los datos al usuario
        String title = JOptionPane.showInputDialog("Enter the title of the book");
        int yearPublication = Integer.parseInt(JOptionPane.showInputDialog("Enter the year of publication of the book"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the book"));
        int id_author = Integer.parseInt(JOptionPane.showInputDialog("Enter the author ID of the book"));

        // Creamos una instancia del libro
        Book objBook = new Book();

        objBook.setTitle(title);
        objBook.setYearPublication(yearPublication);
        objBook.setPrice(price);
        objBook.setId_author(id_author);

        // Llamamos al metodo de insercion y guardamos el objeto que retora Author
        objBook = (Book) objBookModel.insert(objBook);

        JOptionPane.showMessageDialog(null, objBook.toString());
    }

    public static void getAll() {

        BookModel objBook = new BookModel();
        String listBook = "BOOK LIST \n";

        for (Object iterador : objBook.findAll()) {
            //Convertimos del Object a Libro
            Book objbook = (Book) iterador;
            listBook += objbook.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listBook);
    }

    public static String getAllString() {

        BookModel objBook = new BookModel();
        String listBook = "BOOK LIST \n";

        for (Object iterador : objBook.findAll()) {
            //Convertimos del Object
            Book objbook = (Book) iterador;
            listBook += objbook.toString() + "\n";
        }

        return listBook;
    }

    public static void getById() {

        int idBook = Integer.parseInt(JOptionPane.showInputDialog("Enter the Book ID "));
        BookModel objBook = new BookModel();

        String listaString = "COINCIDENCIAS \n";

        Book book = objBook.findById(idBook);

        if (book != null) {
            listaString += book.toString();

        } else {

            listaString += "Book not found";
        }

        JOptionPane.showMessageDialog(null, listaString);

    }

    public static void getByName(){

        String name = JOptionPane.showInputDialog("Insert name of Book ");
        BookModel objBook = new BookModel();

        String listaString = "COINCIDENCIAS \n";
        for (Book iterador: objBook.findByName(name)){
            listaString += iterador.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listaString);

    }

    public static void update() {

        BookModel objBook = new BookModel();

        String listBook = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBook +"\nEnter the ID of the book to edit"));

        Book objbook = objBook.findById(idUpdate);

        if (objbook == null) {
            JOptionPane.showMessageDialog(null, "Book not found");

        } else {

            String title = JOptionPane.showInputDialog(null, "Enter new name", objbook.getTitle());
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the book", objbook.getPrice()));
            int id_author = Integer.parseInt(JOptionPane.showInputDialog("Enter the author ID of the book", objbook.getId_author()));
            int yearPublication = Integer.parseInt(JOptionPane.showInputDialog("Enter the new year of publication of the book.", objbook.getYearPublication()));


            objbook.setTitle(title);
            objbook.setPrice(price);
            objbook.setId_author(id_author);
            objbook.setYearPublication(yearPublication);

            objBook.update(objbook);
        }
    }

    public static void delete() {

        BookModel objBookModel = new BookModel();

        String listBook = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBook + "\n Enter the Id the book to delete: "));

        Book objBook = objBookModel.findById(idDelete);


        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "Book not found");

        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the Book ? \n" + objBook.toString());

            if (confirm == 0) objBookModel.delete(objBook);

        }
    }

    public static void getByAuthor() {

        int id_author = Integer.parseInt(JOptionPane.showInputDialog("Enter the author ID"));
        BookModel objBook = new BookModel();

        String listaString = "COINCIDENCIAS \n";

        Book book = objBook.consultAuthor(id_author);

        if (book != null) {
            listaString += book.toString();

        } else {

            listaString += "Author ID not found";
        }

        JOptionPane.showMessageDialog(null, listaString);

    }

}
