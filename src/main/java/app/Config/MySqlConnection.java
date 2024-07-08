package app.Config;
import java.sql.*;

public class MySqlConnection {
    public String url = "jdbc:mysql://localhost:3306/aeroline?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public String user = "root";
    public String password = "admin";

    public Connection ConectarDB() {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(this.url, this.user, this.password);
            return conexion;
        } catch (SQLException e) {
            return null;
        }
       
    }
}
