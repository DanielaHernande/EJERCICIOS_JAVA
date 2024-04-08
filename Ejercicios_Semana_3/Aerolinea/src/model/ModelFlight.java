package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;
import entity.Flight;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelFlight implements CRUD {
    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una vuelo
        Flight objFlight = (Flight) obj;

        try {

            // 3. Escribir el SQL
            String sql = "INSERT INTO flight (destination, date_departure, departure_time, airplane_id) VALUES (?, ?, ?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setString(1, objFlight.getDestination());
            objPrepare.setDate(2, objFlight.getDate_departure());
            objPrepare.setTime(3, objFlight.getDeparture_time());
            objPrepare.setInt(4, objFlight.getAirplane_id());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objFlight.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Flight added successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();

        return objFlight;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listFlight = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM flight \n" +
                    "INNER JOIN airplane ON airplane.id = flight.airplane_id;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear una nueva instancia
                Flight objFlight = new Flight();
                Airplane objAirplane = new Airplane();

                // 6.2 Llenar el objeto con la información de la bd
                objFlight.setId(objResult.getInt("flight.id"));
                objFlight.setDestination(objResult.getString("flight.destination"));
                objFlight.setDate_departure(objResult.getDate("flight.date_departure"));
                objFlight.setDeparture_time(objResult.getTime("flight.departure_time"));
                objFlight.setAirplane_id(objResult.getInt("flight.airplane_id"));

                objAirplane.setId(objResult.getInt("airplane.id"));
                objAirplane.setModel(objResult.getString("airplane.model"));
                objAirplane.setCapacity(objResult.getInt("airplane.capacity"));

                objFlight.setObjAirplane(objAirplane);

                // 6.3 Agregar especialidad a la lista
                listFlight.add(objFlight);
            }

        } catch (SQLException e) {
            System.out.println("ERROR >>" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listFlight;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Convertir el objeto
        Flight objFlight = (Flight) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isUpdate = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "UPDATE flight \n" +
                    "SET destination = ?,\n" +
                    " date_departure = ?,\n" +
                    " departure_time = ?, airplane_id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setString(1, objFlight.getDestination());
            objPrepare.setDate(2, objFlight.getDate_departure());
            objPrepare.setTime(3, objFlight.getDeparture_time());
            objPrepare.setInt(4, objFlight.getAirplane_id());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The flight was successfully upgraded.");
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
        Flight objFlight = (Flight) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM flight WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objFlight.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The flight was successfully eliminated.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public List<Object> findAllDestination(String destination) {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listFlight = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM flight WHERE destination = ?;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setString(1, destination);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Flight objFlight = new Flight();

                objFlight.setId(objResult.getInt("flight.id"));
                objFlight.setDestination(objResult.getString("flight.destination"));
                objFlight.setDate_departure(objResult.getDate("flight.date_departure"));
                objFlight.setDeparture_time(objResult.getTime("flight.departure_time"));
                objFlight.setAirplane_id(objResult.getInt("flight.airplane_id"));

                listFlight.add(objFlight);
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listFlight;
    }

}
