package model;

import database.CRUD;
import database.ConfigDB;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelPaciente implements CRUD {

    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a un paciente
        Paciente objPaciente = (Paciente) obj;

        try {
            // 3. Escribir el SQL
            String sql = "INSERT INTO paciente (nombre, apellidos, fecha_nacimiento, documento_identidad) VALUES (?, ?, ?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a lso ? ?
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, String.valueOf(objPaciente.getFecha_nacimiento()));
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                objPaciente.setId_paciente(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Patient added correctly.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 9. Cerramos la conexion
        ConfigDB.closeConnection();

        return objPaciente;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista para guardar lo que nos devuelve el controlador
        List<Object> listPaciente = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query SQL
            String sql = "SELECT * FROM paciente;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el query
            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientrar hay un resultado sgte hacer
            while (objResult.next()) {

                // 6.1 Crear un medico
                Paciente objPaciente = new Paciente();

                // 6.2 Llenar el objeto con la información de la bd
               objPaciente.setId_paciente(objResult.getInt("id_paciente"));
               objPaciente.setNombre(objResult.getString("nombre"));
               objPaciente.setApellidos(objResult.getString("apellidos"));
               objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
               objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                // 6.3 Agregar un medico a la lista
                listPaciente.add(objPaciente);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexion
        ConfigDB.closeConnection();

        return listPaciente;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertirlo a un paciente
        Paciente objPaciente = (Paciente) obj;

        // 3. Variable para comocer el estado de la accion
        boolean isUpdated = false;

        try {

            // 4. Crear la sentencia SQL
            String sql = "UPDATE paciente SET nombre = ?, " +
                    "apellidos = ?, fecha_nacimiento = ?, " +
                    "documento_identidad = ? WHERE id_paciente = ?;";

            // 5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Asignar valores a los paramentros del Query

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, String.valueOf(objPaciente.getFecha_nacimiento()));
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

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
        Paciente objPaciente = (Paciente) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objPaciente.getId_paciente());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The patient was correctly eliminated.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public static Paciente findById(int id_paciente) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos retornar
        Paciente objPaciente = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM paciente WHERE id_paciente = ?;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, (id_paciente));

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objPaciente = new Paciente();

                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objPaciente;
    }

    public List<Paciente> findByName(String nombre) {

        //Creamos la lista
        List<Paciente> listPaciente = new ArrayList<>();

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            //Sentencia SQL
            String slq = "SELECT * FROM paciente WHERE nombre LIKE ?;";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1,"%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Paciente objPaciente = new Paciente();

                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPaciente.add(objPaciente);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listPaciente;
    }
}
