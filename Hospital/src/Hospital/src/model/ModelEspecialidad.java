package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelEspecialidad implements CRUD {

    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una especialidad
        Especialidad objEspecialidad = (Especialidad) obj;

        try {

            // 3. Escribir el SQL
            String sql = "INSERT INTO especialidad (nombre, descripcion) VALUES (?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objEspecialidad.setId_especialidad(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Specialty added correctly.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexión
        ConfigDB.closeConnection();

        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listaEspecialidades = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM especialidad;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resulatdo (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientras hay un resulatdo siguiente hacer
            while (objResult.next()) {

                // 6.1 Crear una especialidad
                Especialidad objEspecialidad = new Especialidad();

                // 6.2 Llenar el objeto con la información de la bd
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                // 6.3 Agregar especialidad a la lista
                listaEspecialidades.add(objEspecialidad);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listaEspecialidades;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Especialidad objEspecialidad = (Especialidad) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objEspecialidad.getId_especialidad());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The specialty was correctly eliminated.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Especialidad findById(int id_especialidad) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos retornar
        Especialidad objEspecialidad = null;

        try {

            //3. Sentencia SQL
            String sql = "SELECT * FROM especialidad WHERE id_especialidad = ?;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id_especialidad);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objEspecialidad = new Especialidad();

                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objEspecialidad;
    }

    /*public List<Especialidad> findByName(String name) {

        // Creamos la lista
        List<Especialidad> listEspecialidad = new ArrayList<>();

        // Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        try {
            // Sentencia SQL
            String



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        return listEspecialidad;
    }*/

}
