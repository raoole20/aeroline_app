package app.aeroline.aeroline;

import java.sql.Connection;

import app.Config.MySqlConnection;
import app.aeroline.FrameError;

/**
 *
 * @author Your name
 */
public class Main {
    public static void main(String[] param) {  
        MySqlConnection _conexion = new MySqlConnection();
        Connection conexion = _conexion.ConectarDB();

        if(conexion != null) {
            System.out.println("Connection established");
        } else {
            FrameError.ViewError("Error connecting to the database");
        }
    }
}