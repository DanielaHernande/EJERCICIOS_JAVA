package model;

import database.CRUD;
import database.ConfigDB;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelMedico implements CRUD {

    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una medico
        Medico objMedico = (Medico) obj;

        try {

            // 3. Escribir el SQL
            String sql = "INSERT INTO medico (nombre, apellidos, id_especialidad) VALUES (?, ?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setString(1, objMedico.getNombre());
            objPrepare.setString(2, objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getId_especialidad());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objMedico.setId_especialidad(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Doctor added correctly.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexión
        ConfigDB.closeConnection();

        return objMedico;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista para guardar lo que nos devuelve el controlador
        List<Object> listMedico = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query SQL
            String sql = "SELECT * FROM medico;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el query
            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientrar hay un resultado sgte hacer
            while (objResult.next()) {

                // 6.1 Crear un medico
                Medico objMedico = new Medico();

                // 6.2 Llenar el objeto con la información de la bd
                objMedico.setId_medico(objResult.getInt("id"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));

                // 6.3 Agregar un medico a la lista
                listMedico.add(objMedico);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexion
        ConfigDB.closeConnection();

        return listMedico;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertirlo a un medico
        Medico objMedico = (Medico) obj;

        // 3. Variable para comocer el estado de la accion
        boolean isUpdated = false;

        try {

            // 4. Crear la sentencia SQL
            String sql = "UPDATE medico SET id_especialidad = ? WHERE id = ?;";

            // 5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Asignar valores a los paramentros del Query
            objPrepare.setInt(1, objMedico.getId_especialidad());
            objPrepare.setInt(2, objMedico.getId_medico());

            //7. Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();

            // Si es mayor a cera significa que si actualizo
            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Medico objMedico = (Medico) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM medico WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objMedico.getId_medico());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The doctor was correctly eliminated.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Medico findById(int id_medico) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos retornar
        Medico objMedico = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM medico WHERE id = ?;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id_medico);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objMedico = new Medico();

                objMedico.setId_medico(objResult.getInt("id"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objMedico;
    }
}
