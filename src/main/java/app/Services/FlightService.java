package app.Services;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import app.Config.MySqlConnection;
import app.Models.DTOs.Flight;

public class FlightService {
    private MySqlConnection connection;

    public FlightService(MySqlConnection connection) {
        this.connection = connection;
    }

    public CompletableFuture<ArrayList<Flight>> getFlightsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            if (this.connection.error) {
                System.out.println("Error: getFlights, no hay conexion a la base de datos");
                return null;
            }
            try {
                var query = "SELECT * FROM flight";
                var result = this.connection.executeQuery(query);
                var flights = new ArrayList<Flight>();
    
                if (result == null) {
                    System.out.println("Flights not data");
                    return null;
                }
    
                while (result.next()) {
                    int id = result.getInt("id");
                    var flightNumber = result.getString("flightNumber");
                    var departure = result.getString("departure");
                    var arrival = result.getString("arrival");
                    var logoURL = result.getString("logoURL");
                    var departureTime = result.getString("departureTime");
                    var arrivalTime = result.getString("arrivalTime");
                    var price = result.getString("price");
                    var airline = result.getString("airline");
                    var duration = result.getString("duration");
    
                    var flight = new Flight(
                            id,
                            flightNumber,
                            departure,
                            arrival,
                            logoURL,
                            departureTime,
                            arrivalTime,
                            price,
                            airline,
                            duration
                    );
                    flights.add(flight);
                }
    
                return flights;
            } catch (Exception e) {
                System.out.println("Error: getFlights, no se pudo ejecutar la consulta");
                return null;
            }
        });
    }
}
