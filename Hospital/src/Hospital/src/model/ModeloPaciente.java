package model;

import database.CRUD;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModeloPaciente implements CRUD {

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
            objPrepare.setDate(3, objPaciente.getFecha_nacimiento());
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                objPaciente.setId_paciente(objRest.getInt(1));
                
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }


        return null;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
