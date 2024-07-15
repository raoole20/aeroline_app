package app.Config;
import java.sql.*;

public class MySqlConnection {
    public String url = "jdbc:mysql://localhost:3306/aeroline?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public String user = "root";
    public String password = "admin";

    private Connection conexion;
    private Statement statement;
    public Boolean error = false;

    public MySqlConnection() {
        try {
            this.conexion = DriverManager.getConnection(this.url, this.user, this.password);
            this.statement = this.conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            this.error = true;
        }
    }

    public Connection ConectarDB() {
        if(this.error) {
            return null;
        }

        return this.conexion;
    }

    public Statement getStatement() {
        if(this.error) {
            return null;
        }

        return this.statement;
    }

    public ResultSet executeQuery(String query) {
        try {
            return this.statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error executing query");
            return null;
        }
    }

    public PreparedStatement prepareUpdateQuery(String query) {
        try (PreparedStatement preparedStatement = this.conexion.prepareStatement(query)) {

            return preparedStatement;
        } catch (SQLException e) {
            System.out.println("Error executing update");
            return null;
        }
    }

    public Connection getConnection() {
        return this.conexion;
    }
}
