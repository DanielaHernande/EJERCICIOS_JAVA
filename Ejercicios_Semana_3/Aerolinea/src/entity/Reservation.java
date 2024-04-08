package entity;

import java.sql.Date;

public class Reservation {

    private int id;
    private int passenger_id;
    private int flight_id;
    private Date reservation_date;
    private String seat;

    private Passenger objPassenger;
    private Flight objFlight;

    // Constructores
    public Reservation() {
    }

    public Reservation(int passenger_id, int flight_id, Date reservation_date, String seat, Passenger objPassenger, Flight objFlight) {
        this.passenger_id = passenger_id;
        this.flight_id = flight_id;
        this.reservation_date = reservation_date;
        this.seat = seat;
        this.objPassenger = objPassenger;
        this.objFlight = objFlight;
    }

    // Metodos getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Passenger getObjPassenger() {
        return objPassenger;
    }

    public void setObjPassenger(Passenger objPassenger) {
        this.objPassenger = objPassenger;
    }

    public Flight getObjFlight() {
        return objFlight;
    }

    public void setObjFlight(Flight objFlight) {
        this.objFlight = objFlight;
    }

    // toString

    @Override
    public String toString() {
        return "Reservation: " +
                "Reservation date= " + reservation_date +
                "Seat= " + seat + '\'' +
                "Passenger= " + objPassenger.getName() +
                "Flight= " + objFlight.getDestination();
    }
}
