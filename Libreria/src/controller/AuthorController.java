package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;

public class AuthorController {

    public static void getAll() {

        AuthorModel objAuthor = new AuthorModel();
        String listAuthor = "🤷‍♂️ AUTHORS LIST \n";

        for (Object iterador : objAuthor.findAll()) {
            //Convertimos del Object a un Author
            Author objauthor = (Author) iterador;
            listAuthor += objauthor.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAuthor);
    }

    public static String getAllString() {

        AuthorModel objAuthor = new AuthorModel();
        String listAuthor = "🤷‍♂️ AUTHORS LIST \n";

        for (Object iterador : objAuthor.findAll()) {
            //Convertimos del Object
            Author objauthor = (Author) iterador;
            listAuthor += objauthor.toString() + "\n";
        }

        return listAuthor;
    }

    public static void create() {

        // Usamos el modelo de author
        AuthorModel objAuthorModel = new AuthorModel();

        // Pedimos los datos al usuario
        String name = JOptionPane.showInputDialog("Enter the author's name");
        String nacionality = JOptionPane.showInputDialog("Enter the author's nationality");

        // Creamos una instancia de Author
        Author objAuthor = new Author();

        objAuthor.setName(name);
        objAuthor.setNacionality(nacionality);

        // Llamamos al metodo de insercion y guardamos el objeto que retora Author
        objAuthor = (Author) objAuthorModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }

    public static void delete() {

        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthor = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthor + "\n Enter the Id the author to delete: "));

        Author objAuthor = objAuthorModel.findById(idDelete);


        if (objAuthor == null) {
            JOptionPane.showMessageDialog(null, "Author not found");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the Author ? \n" + objAuthor.toString());

            if (confirm == 0) objAuthorModel.delete(objAuthor);

        }
    }

    public static void getById() {

        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog("Enter the Author ID "));
        AuthorModel objAuthor = new AuthorModel();

        String listaString = "COINCIDENCIAS \n";

        Author author = objAuthor.findById(idAuthor);

        if (author != null) {
            listaString += author.toString();

        } else {

            listaString += "Author not found";
        }

        JOptionPane.showMessageDialog(null, listaString);

    }

    public static void update() {

        AuthorModel objAuthor = new AuthorModel();

        String listAuthor = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAuthor +"\nEnter the ID of the author to edit"));

        Author objauthor = objAuthor.findById(idUpdate);

        if (objauthor == null) {
            JOptionPane.showMessageDialog(null, "Author not found");

        } else {
            String name = JOptionPane.showInputDialog(null, "Enter new name", objauthor.getName());
            String nacionality = JOptionPane.showInputDialog("Introduce new nationality", objauthor.getNacionality());

            objauthor.setName(name);
            objauthor.setNacionality(nacionality);

            objAuthor.update(objauthor);
        }
    }


}
