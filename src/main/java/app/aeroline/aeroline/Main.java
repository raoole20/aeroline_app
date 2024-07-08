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
        /**
         **Init all requeried instances
         * Estoy usando una arquitectura de tipo 'squeleton'
         * es decir, solo tendre una instancia de mis clases 
         * para que sea mas facil trabajar con las referencias
         */
        var _conexion = new MySqlConnection();
        var _app = new App();

        // se conecta a la base de datos
        var conexion = _conexion.ConectarDB();
        if(conexion != null) {
            _app.printMainMenu();
        } else {
            FrameError.ViewError("Error connecting to the database");
        }
    }
}