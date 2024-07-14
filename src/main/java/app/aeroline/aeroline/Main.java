package app.aeroline.aeroline;

import java.sql.SQLException;

import app.Config.MySqlConnection;
import app.Services.FlightService;
import app.aeroline.FrameError;

/**
 *
 * @author Your name
 */
public class Main {
    public static void main(String[] param) throws SQLException {  
        /**
         **Init all requeried instances
         * Estoy usando una arquitectura de tipo 'squeleton'
         * es decir, solo tendre una instancia de mis clases 
         * para que sea mas facil trabajar con las referencias
         */
        var _conexion = new MySqlConnection();
        var conexion = _conexion.ConectarDB();

        if(conexion != null) {
            var flightService = new FlightService(_conexion);

            var _app = new App(flightService);
            _app.printMainMenu();
        } else {
            FrameError.ViewError("Error connecting to the database");
        }
    }
}