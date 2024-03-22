package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        // Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        // Convertir el obj en Libro
        Book objBook = (Book) obj;

        try {

            // Escribir SQL
            String sql = "INSERT INTO book(title, yearPublication, price, id_author) VALUES (?, ?, ?, ?);";

            // Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Asignar valores a los ? ?
            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setInt(2, objBook.getYearPublication());
            objPrepare.setDouble(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getId_author());

            // Ejecutar el query
            objPrepare.execute();

            // Obtener el resultado con el id
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Iterar mientras haya un registro
            while (objRest.next()) {
                objBook.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Book insertion was successful :)");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();

        return objBook;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> lisyBook = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en Sql
            String sql = "SELECT * FROM book;";

            //4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutar el query y obtener el resultado (ResulSet)

            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientras haya un resultado siguiente hacer:
            while (objResult.next()) {

                // 6.1 Crear un coder
                Book objBook = new Book();

                //6.2 Llenar el objeto con la información de la base de datos del objeto ques está iterando
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYearPublication(objResult.getInt("YearPublication"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setId_author(objResult.getInt("id_author"));

                //6.3 Agregamos el Coder a la lista
                lisyBook.add(objBook);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //Paso 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return lisyBook;
    }

    @Override
    public boolean update(Object object) {

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Book objBook = (Book) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated=false;

        try {
            //4. Creamos la sentencia SQL
            String sql  = "UPDATE book SET title = ?, yearPublication = ?, price = ?, id_author = ? WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los ? parámetros de Query
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setInt(2,objBook.getYearPublication());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getId_author());
            objPrepare.setInt(5, objBook.getId());

            //7. Ejecutamos el query
            int rowAffected  = objPrepare.executeUpdate();

            if (rowAffected > 0){
                isUpdated= true;
                JOptionPane.showMessageDialog(null,"Book successfully updated.");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }finally {
            //8. Cerrar la conexión
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        // Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        // Convertir el objeto
        Book objBook = (Book) obj;

        // Creamos una variable de estado
        boolean isDeleted = false;

        try {

            // Escribir sentencia SQL
            String sql = "DELETE FROM book WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Dar valor a ?
            objPrepare.setInt(1, objBook.getId());

            //  Ejecutamos el Query
            int totalAffectRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si eliminó algo
            if (totalAffectRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Book successfully removed");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Book findById(int id) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear un libro que vamos retornar
        Book objBook = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM book WHERE id = ?;";
            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objBook = new Book();

                objBook.setId(objResult.getInt("id"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setId_author(objResult.getInt("id_author"));
                objBook.setYearPublication(Integer.parseInt(objResult.getString("yearPublication")));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objBook;
    }

    public List<Book> findByName(String name) {

        //Creamos la lista
        List<Book> listBook = new ArrayList<>();

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia SQL
            String slq = "SELECT * FROM book WHERE title LIKE ?;";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1,"%"+name+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Book objBook = new Book();

                objBook.setId(objResult.getInt("id"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setId_author(objResult.getInt("id_author"));
                objBook.setYearPublication(Integer.parseInt(objResult.getString("yearPublication")));

                listBook.add(objBook);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listBook;
    }

    public List<Book> consultAuthor(int id_author) {

        //Creamos la lista
        List<Book> listBook = new ArrayList<>();

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia SQL
            String slq = "SELECT * FROM book WHERE id_author = ?;";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1,"%"+id_author+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Book objBook = new Book();

                objBook.setId(objResult.getInt("id"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setId_author(objResult.getInt("id_author"));
                objBook.setYearPublication(Integer.parseInt(objResult.getString("yearPublication")));

                listBook.add(objBook);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listBook;
    }


}
