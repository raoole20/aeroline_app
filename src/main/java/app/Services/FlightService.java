package app.Services;

import java.sql.*;

import app.Config.MySqlConnection;

public class FlightService {
    private MySqlConnection connection;

    public FlightService(MySqlConnection connection) {
        this.connection = connection;
    }

    public Object getFlights() {
       if(this.connection.error) {
            System.out.println("Error: getFlights, no hay conexion a la base de datos");
           return null;
       }
       
         try {
              var query = "SELECT * FROM vuelos";
              var result = this.connection.executeQuery(query);
              return result;
         } catch (Exception e) {
              System.out.println("Error: getFlights, no se pudo ejecutar la consulta");
              return null;
         }
    }
}
