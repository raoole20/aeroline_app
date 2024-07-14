package app.Models.DTOs;

public class Flight {
    public int id;
    public String flightNumber;
    public String departure;
    public String arrival;
    public String logoURL;
    public String departureTime;
    public String arrivalTime;
    public String price;
    public String airline;
    public String duration;

    public Flight(
        int id,
        String flightNumber,
        String departure,
        String arrival,
        String logoURL,
        String departureTime,
        String arrivalTime,
        String price,
        String airline,
        String duration
    ) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.airline = airline;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getPrice() {
        return price;
    }

    public String getAirline() {
        return airline;
    }

    public String getDuration() {
        return duration;
    }
}
