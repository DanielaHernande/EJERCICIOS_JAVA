package entity;

import java.sql.Date;
import java.sql.Time;

public class Flight {

    private int id;
    private String destination;
    private Date date_departure;
    private Time departure_time;
    private int airplane_id;

    private Airplane objAirplane;

    // Constructores
    public Flight() {
    }

    public Flight(String destination, Date date_departure, Time departure_time, int airplane_id, Airplane objAirplane) {
        this.destination = destination;
        this.date_departure = date_departure;
        this.departure_time = departure_time;
        this.airplane_id = airplane_id;
        this.objAirplane = objAirplane;
    }

    // Metodos getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate_departure() {
        return date_departure;
    }

    public void setDate_departure(Date date_departure) {
        this.date_departure = date_departure;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public int getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(int airplane_id) {
        this.airplane_id = airplane_id;
    }

    public Airplane getObjAirplane() {
        return objAirplane;
    }

    public void setObjAirplane(Airplane objAirplane) {
        this.objAirplane = objAirplane;
    }

    // toString
    @Override
    public String toString() {
        return "\nFlight: " +
                " Destination= " + destination + '\'' +
                " Date_departure= " + date_departure +
                " Departure_time= " + departure_time +
                " Airplane_id= " + airplane_id;
    }
}
