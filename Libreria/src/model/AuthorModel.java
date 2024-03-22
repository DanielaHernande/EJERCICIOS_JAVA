package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        // Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        // Convertir el obj en Autor
        Author objAuthor = (Author) obj;

        try {

            // Escribir SQL
            String sql = "INSERT INTO author(name, nacionality) VALUES (?, ?);";

            // Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Asignar valores a los ? ?
            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNacionality());

            // Ejecutar el query
            objPrepare.execute();

            // Obtener el resultado con el id
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // I terar mientras haya un registro
            while (objRest.next()) {
                objAuthor.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Author insertion was successful :)");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();

        return objAuthor;
    }

    @Override
    public List<Object> findAll() {

        // Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listAuthor = new ArrayList<>();

        //  Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //  Escribimos el query en Sql
            String sql = "SELECT * FROM author;";

            // Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Ejecutar el query y obtener el resultado (ResulSet)

            ResultSet objResult = objPrepare.executeQuery();

            //  Mientras haya un resultado siguiente hacer:
            while (objResult.next()) {

                //  Crear un coder
                Author objAuthor = new Author();

                // Llenar el objeto con la información de la base de datos del objeto ques está iterando
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNacionality(objResult.getString("nacionality"));

                // Agregamos el Coder a la lista
                listAuthor.add(objAuthor);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        // Cerrar la conexión
        ConfigDB.closeConnection();

        return listAuthor;
    }

    @Override
    public boolean update(Object object) {

        // Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // Convertir el objeto
        Author objAuthor = (Author) object;

        // Variable bandera para saber si se actualizó
        boolean isUpdated=false;

        try {
            // Creamos la sentencia SQL
            String sql  = "UPDATE author SET name = ?, nacionality = ? WHERE id = ?;";

            // Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // Dar valor a los ? parámetros de Query
            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNacionality());
            objPrepare.setInt(3,objAuthor.getId());

            // Ejecutamos el query
            int rowAffected  = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdated= true;
                JOptionPane.showMessageDialog(null,"The update was successful.");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            // Cerrar la conexión
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        // Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        // Convertir el objeto
        Author objAuthor = (Author) obj;

        // Creamos una variable de estado
        boolean isDeleted = false;

        try {

            // Escribir sentencia SQL
            String sql = "DELETE FROM author WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Dar valor a ?
            objPrepare.setInt(1, objAuthor.getId());

            //  Ejecutamos el Query
            int totalAffectRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si eliminó algo
            if (totalAffectRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Author successfully removed");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Author findById(int id) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Crear el coder que vamos retornar
        Author objAuthor = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM author WHERE id = ?;";
            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objAuthor = new Author();

                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNacionality(objResult.getString("nacionality"));
                objAuthor.setId(objResult.getInt("id"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objAuthor;
    }
}

