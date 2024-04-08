package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;
import entity.Flight;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelPassenger implements CRUD {
    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una avion
        Passenger objPessenger = (Passenger) obj;

        try {

            // 3. Escribir el SQL
            String sql = " INSERT INTO passenger(name, last_name, identity_document) VALUES (?,?,?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setString(1, objPessenger.getName());
            objPrepare.setString(2, objPessenger.getLast_name());
            objPrepare.setString(3, objPessenger.getIdentity_document());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objPessenger.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Passenger added correctly.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexión
        ConfigDB.closeConnection();

        return objPessenger;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listPassenger = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM passenger;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear una nueva instancia de avión
                Passenger objPassenger = new Passenger();

                // 6.2 Llenar el objeto con la información de la bd
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLast_name(objResult.getString("last_name"));
                objPassenger.setIdentity_document(objResult.getString("identity_document"));

                // 6.3 Agregar especialidad a la lista
                listPassenger.add(objPassenger);
            }

        } catch (SQLException e) {
            System.out.println("ERROR>>" + e.getMessage());

        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listPassenger;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Convertir el objeto
        Passenger objPassenger = (Passenger) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isUpdate = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = " UPDATE passenger \n" +
                    " SET name = ?, \n" +
                    " last_name = ?, \n" +
                    " identity_document = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLast_name());
            objPrepare.setString(3, objPassenger.getIdentity_document());


            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The passenger was successfully upgraded.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isUpdate;

    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Passenger objPassenger = (Passenger) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = " DELETE FROM passenger WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objPassenger.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The passenger was successfully removed.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public List<Object> findAllName(String name) {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listPassenger = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = " SELECT * FROM passenger WHERE name = ?;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setString(1, name);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Passenger objPassenger = new Passenger();

                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLast_name(objResult.getString("last_name"));
                objPassenger.setIdentity_document(objResult.getString("identity_document"));

                listPassenger.add(objPassenger);
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listPassenger;
    }
}
