import controller.AuthorController;
import controller.BookController;
import model.BookModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option = "";

        do {

            option = JOptionPane.showInputDialog("""
                    1. Options Authors.
                    2. Options Books.
                    3. Consult authors with their books.
                    4. Exit.        
                                        
                    Choose an option:
                    """);

            switch (option) {

                case "1":
                    String option1 = "";

                    do {
                        option1 = JOptionPane.showInputDialog("""
                                1. Insert a new Author.
                                2. Consult all authors.
                                3. Consult an author by ID.
                                4. Update information about an author.
                                5. Delete an author.
                                6. Exit.
                                                                
                                Choose an option:
                                """);

                        switch (option1) {

                            case "1":
                                AuthorController.create();
                                break;

                            case "2":
                                AuthorController.getAll();
                                break;

                            case "3":
                                AuthorController.getById();
                                break;

                            case "4":
                                AuthorController.update();
                                break;

                            case "5":
                                AuthorController.delete();
                                break;
                        }

                    } while (!option1.equals("6"));

                    break;

                case "2":
                    String option2 = "";

                    do {

                        option2 = JOptionPane.showInputDialog("""
                                1. Insert a new Book.
                                2. Consult all books.
                                3. Consult a book by ID.
                                4. Consult a book by title. 
                                5. Update information about a book.
                                6. Delete a book.
                                7. Exit.
                                                                
                                Choose an option:
                                """);

                        switch (option2) {

                            case "1":
                                BookController.create();
                                break;

                            case "2":
                                BookController.getAll();
                                break;

                            case "3":
                                BookController.getById();
                                break;

                            case "4":
                                BookController.getByName();
                                break;

                            case "5":
                                BookController.update();
                                break;

                            case "6":
                                BookController.delete();
                                break;
                        }

                    } while (!option2.equals("7"));

                    break;

                case "3":
                    BookController.getByAuthor();
                    break;
            }


        } while (!option.equals("4"));

        JOptionPane.showMessageDialog(null, "Chao :)");

    }
}