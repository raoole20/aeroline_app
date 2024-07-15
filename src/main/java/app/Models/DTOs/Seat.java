package app.Models.DTOs;

public class Seat {
    public int id;
    public String flightID;
    public int status; 
    public String seatsCode;

    public Seat(int id, String flightID, int status, String seatsCode) {
        this.id = id;
        this.flightID = flightID;
        this.status = status;
        this.seatsCode = seatsCode;
    }
}
