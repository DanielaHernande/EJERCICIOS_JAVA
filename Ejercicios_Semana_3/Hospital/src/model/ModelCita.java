package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelCita implements CRUD {

    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una medico
        Cita objCita = (Cita) obj;

        try {

            // 3. Escribir el SQL
            String sql = "INSERT INTO cita (id_paciente, id_medico, fecha_cita, hora_cita, motivo) VALUES (?, ?, ?, ?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setInt(1, objCita.getId_paciente());
            objPrepare.setInt(2, objCita.getId_medico());
            objPrepare.setDate(3, (Date) objCita.getFecha_cita());
            objPrepare.setTime(4, objCita.getHora_cita());
            objPrepare.setString(5, objCita.getMotivo());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objCita.setId_cita(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Appointment successfully added.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexión
        ConfigDB.closeConnection();

        return objCita;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista para guardar lo que nos devuelve el controlador
        List<Object> listCitas = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query SQL
            String sql = "SELECT * FROM cita; ";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el query
            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientrar hay un resultado sgte hacer
            while (objResult.next()) {

                // 6.1 Crear un medico
                Cita objCita = new Cita();

                // 6.2 Llenar el objeto con la información de la bd
                objCita.setId_paciente(objResult.getInt("id_paciente"));
                objCita.setId_medico(objResult.getInt("id_medico"));
                objCita.setFecha_cita(objResult.getDate("fecha_cita"));
                objCita.setHora_cita(objResult.getTime("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

                // 6.3 Agregar un medico a la lista
                listCitas.add(objCita);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexion
        ConfigDB.closeConnection();

        return listCitas;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertirlo a una cita
        Cita objCita = (Cita) obj;

        // 3. Variable para comocer el estado de la accion
        boolean isUpdated = false;

        try {

            // 4. Crear la sentencia SQL
            String sql = "UPDATE cita SET fecha_cita = ? WHERE id_cita = ?;";

            // 5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Asignar valores a los paramentros del Query
            objPrepare.setDate(1, (Date) objCita.getFecha_cita());
            objPrepare.setDate(2, (Date) objCita.getFecha_cita());

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
        Cita objCita = (Cita) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM cita WHERE id_cita = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objCita.getId_cita());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The Appointment was correctly eliminated.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Cita findByDate(Date fecha_cita) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        List<Cita> citas = new ArrayList<>();

        //2. Crear la cita que vamos retornar
        Cita objCita = null;
        Paciente objPaciente = null;
        Medico objMedico = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT paciente.nombre, medico.nombre, cita.fecha_cita\n" +
                    "FROM paciente\n" +
                    "INNER JOIN cita ON paciente.id_paciente = cita.id_paciente\n" +
                    "INNER JOIN medico ON medico.id_medico = cita.id_paciente\n" +
                    "WHERE cita.fecha_cita = ?;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setDate(1, fecha_cita);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objCita = new Cita();
                objMedico = new Medico();
                objPaciente = new Paciente();

                objCita.setId_paciente(objResult.getInt("id_paciente"));
                objCita.setId_medico(objResult.getInt("id_medico"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objMedico.setNombre(objResult.getString("nombre"));
                objCita.setFecha_cita(objResult.getDate("fecha_cita"));
                objCita.setHora_cita(objResult.getTime("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
                citas.add(objCita);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objCita;
    }
    public Cita findById(int id_cita) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos retornar
        Cita objCita = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM cita WHERE id_cita = ?;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id_cita);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objCita = new Cita();

                objCita.setId_paciente(objResult.getInt("id_paciente"));
                objCita.setId_medico(objResult.getInt("id_medico"));
                objCita.setFecha_cita(objResult.getDate("fecha_cita"));
                objCita.setHora_cita(objResult.getTime("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objCita;
    }


}
