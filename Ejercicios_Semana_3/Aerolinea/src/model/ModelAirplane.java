package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelAirplane implements CRUD {


    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una médico
        Airplane objAirplane = (Airplane) obj;

        try {

            // 3. Escribir el SQL
            String sql = "INSERT INTO airplane(model, capacity) VALUES (?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setString(1, objAirplane.getModel());
            objPrepare.setInt(2, objAirplane.getCapacity());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objAirplane.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Airplane added correctly.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexión
        ConfigDB.closeConnection();

        return objAirplane;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listAirplane = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM airplane;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resulatdo (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear un avion
                Airplane objAirplane = new Airplane();

                // 6.2 Llenar el objeto con la información de la bd
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getInt("capacity"));

                // 6.3 Agregar especialidad a la lista
                listAirplane.add(objAirplane);
            }

        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listAirplane;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Airplane objAirplane = (Airplane) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM airplane WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objAirplane.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {

                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Aircraft successfully removed.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" +e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Airplane findById(int id) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear el coder que vamos retornar
        Airplane objAirplane = null;

        try {

            //3. Sentencia SQL
            String sql = "SELECT * FROM airplane WHERE id = ?;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {

                objAirplane = new Airplane();

                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getInt("capacity"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAirplane;
    }
}
