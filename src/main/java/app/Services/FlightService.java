package app.Services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import app.Config.MySqlConnection;
import app.Models.DTOs.Flight;
import app.Models.DTOs.Seat;
import app.components.Seats.SeatsButton;

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
                            duration);
                    flights.add(flight);
                }

                return flights;
            } catch (Exception e) {
                System.out.println("Error: getFlights, no se pudo ejecutar la consulta");
                return null;
            }
        });
    }

    public CompletableFuture<ArrayList<Flight>> getFlightByCountries(
        String param
    ) {
        return CompletableFuture.supplyAsync(() -> {
            if (this.connection.error) {
                System.out.println("Error: getFlights, no hay conexion a la base de datos");
                return null;
            }
            try {
                var query = "SELECT * FROM flight where departure like '%" + param + "%' or arrival like '%" + param + "%'";
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
                            duration);
                    flights.add(flight);
                }

                return flights;
            } catch (Exception e) {
                System.out.println("Error: getFlights, no se pudo ejecutar la consulta");
                return null;
            }
        });
    }

    public CompletableFuture<ArrayList<Flight>> getFlightFlithByFilters(
        String departureFilter,
        String arrivalFilter
    ) {
        return CompletableFuture.supplyAsync(() -> {
            if (this.connection.error) {
                System.out.println("Error: getFlights, no hay conexion a la base de datos");
                return null;
            }
            try {
                var query = "select * from flight where";
                var isAddedDeparture = false;

                if(!departureFilter.isEmpty() && departureFilter != "Select a country...") {
                    query += " departure = '" + departureFilter + "'";
                    isAddedDeparture = true;
                }
                if(!arrivalFilter.isEmpty() && arrivalFilter != "Select a country...") {
                    query += isAddedDeparture ? " AND" : "";
                    query += " arrival = '" + arrivalFilter + "'";
                }

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
                            duration);
                    flights.add(flight);
                }

                return flights;
            } catch (Exception e) {
                System.out.println("Error: getFlights, no se pudo ejecutar la consulta");
                return null;
            }
        });
    }
    public CompletableFuture<ArrayList<Seat>> getSeatsByFlightID(int seatsID) {
        return CompletableFuture.supplyAsync(() -> {
            if (this.connection.error) {
                System.out.println("Error: getSeatsByFlightID, no hay conexion a la base de datos");
                return null;
            }
            try {
                var query = "SELECT * FROM seats where flightID =  " + seatsID;
                var result = this.connection.executeQuery(query);
                var seats = new ArrayList<Seat>();

                if (result == null) {
                    System.out.println("Flights not data");
                    return null;
                }

                while (result.next()) {
                    int id = result.getInt("id");
                    var flightID = result.getString("flightID");
                    int status = result.getInt("status");
                    var seatsCode = result.getString("seatsCode");

                    var seat = new Seat(
                            id,
                            flightID,
                            status,
                            seatsCode);
                    seats.add(seat);
                }

                return seats;
            } catch (Exception e) {
                System.out.println("Error: getSeatsByFlightID, no se pudo ejecutar la consulta");

                return null;
            }
        });
    }

    public CompletableFuture<Boolean> comprarVuelo(
            int flightID,
            ArrayList<SeatsButton> seats,
            int seatsCounts) {
        return CompletableFuture.supplyAsync(() -> {
            if (this.connection.error) {
                System.out.println("Error: getSeatsByFlightID, no hay conexion a la base de datos");
                return false;
            }
            try {
                var codes = "";
                var size = seatsCounts;
                var controll = 0;

                for (SeatsButton cs : seats) {
                    if (cs.getIsSelected()) {
                        codes += "" + cs.getSeatsCODE();

                        if (controll < size - 1) {
                            codes += ", ";
                            controll++;
                        } else {
                            codes += "";
                        }
                    }
                }
                List<String> codesList = Arrays.asList(codes.split(",\\s*"));

                // Construye la parte de la consulta con los marcadores de posición
                String inSql = codesList.stream().map(code -> "?").collect(Collectors.joining(", "));

                String query = "UPDATE seats SET status = 1 WHERE seatsCODE IN (" + inSql + ") AND flightID = ?";

                try (PreparedStatement pstmt = connection.getConnection().prepareStatement(query)) {
                    // Establece los valores para los marcadores de posición de los códigos
                    int index = 1;
                    for (String code : codesList) {
                        pstmt.setString(index++, code);
                    }
                    // Establece el valor para flightID
                    pstmt.setInt(index, flightID);

                    int affectedRows = pstmt.executeUpdate();
                    System.out.println("Filas afectadas: " + affectedRows);
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Error: getSeatsByFlightID, no se pudo ejecutar la consulta");
                return false;
            }
        });
    }
}
