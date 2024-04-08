package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;
import entity.Flight;
import entity.Passenger;
import entity.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelReservation implements CRUD {
    @Override
    public Object insert(Object obj) {

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el objeto que llego a una vuelo
        Reservation objReservation = (Reservation) obj;

        try {

            // 3. Escribir el SQL
            String sql = " INSERT INTO reservation (passenger_id, flight_id, reservation_date, seat)\n" +
                    " VALUES (?, ?, ?, ?);";

            // 4. Preparar el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valores a los ??
            objPrepare.setInt(1, objReservation.getPassenger_id());
            objPrepare.setInt(2, objReservation.getFlight_id());
            objPrepare.setDate(3, objReservation.getReservation_date());
            objPrepare.setString(4, objReservation.getSeat());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {

                // Podemos obtener el valor tambien con indices
                objReservation.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Passenger successfully added.");


        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listReservations = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM reservation \n" +
                    "INNER JOIN passenger ON passenger.id = reservation.passenger_id\n" +
                    "INNER JOIN flight ON flight.id = reservation.flight_id;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear una nueva instancia
                Reservation objReservation = new Reservation();
                Flight objFlight = new Flight();
                Passenger objPessenger = new Passenger();

                // 6.2 Llenar el objeto con la información de la bd

                objReservation.setId(objResult.getInt("reservation.id"));
                objReservation.setPassenger_id(objResult.getInt("reservation.passenger_id"));
                objReservation.setFlight_id(objResult.getInt("reservation.flight_id"));
                objReservation.setReservation_date(objResult.getDate("reservation.reservation_date"));
                objReservation.setSeat(objResult.getString("reservation.seat"));

                objPessenger.setName(objResult.getString("passenger.name"));
                objFlight.setDestination(objResult.getString("flight.destination"));

                objReservation.setObjFlight(objFlight);
                objReservation.setObjPassenger(objPessenger);


                // 6.3 Agregar especialidad a la lista
                listReservations.add(objReservation);
            }

        } catch (SQLException e) {
            System.out.println("ERROR >>" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listReservations;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Convertir el objeto
        Reservation objReservation = (Reservation) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isUpdate = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "  UPDATE reservation SET passenger_id = ?, \n" +
                    "  flight_id = ?, reservation_date = ?,\n" +
                    "  seat = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objReservation.getPassenger_id());
            objPrepare.setInt(2, objReservation.getFlight_id());
            objPrepare.setDate(3, objReservation.getReservation_date());
            objPrepare.setString(4, objReservation.getSeat());
            // objPrepare.setInt(5, objReservation.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The reservation was successfully upgraded.");
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
        Reservation objReservation = (Reservation) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "  DELETE FROM reservation WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objReservation.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The reservation was successfully removed.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public List<Object> findAllVuelo(String vuelo) {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listFlight = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = " SELECT * FROM flight \n" +
                    " INNER JOIN airplane ON airplane.id  = flight.airplane_id\n" +
                    " WHERE flight.destination = ?;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setString(1, vuelo);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Flight objFlight = new Flight();
                Airplane objAirplane = new Airplane();

                objFlight.setId(objResult.getInt("flight.id"));
                objFlight.setDestination(objResult.getString("flight.destination"));
                objFlight.setDate_departure(objResult.getDate("flight.date_departure"));
                objFlight.setDeparture_time(objResult.getTime("flight.departure_time"));
                objFlight.setAirplane_id(objResult.getInt("flight.airplane_id"));

                objAirplane.setModel(objResult.getString("airplane.model"));

                listFlight.add(objAirplane);
                listFlight.add(objFlight);
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listFlight;
    }

 /*   public static void reservations(boolean[][] listAsientos) {


    }*/

}




