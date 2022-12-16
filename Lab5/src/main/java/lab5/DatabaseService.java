package lab5;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class DatabaseService {
    private String url;
    private String user;
    private String password;

    private Connection connection;

    public DatabaseService(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void createTables() throws SQLException{
        String employee = "CREATE TABLE employee" +
                "(employee_id serial PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "salary NUMERIC(7,1) NOT NULL," +
                "address VARCHAR(255) NOT NULL," +
                "phoneNumber VARCHAR(12) UNIQUE," +
                "isInsuranced BOOLEAN)";
        String supplier = "CREATE TABLE supplier(" +
                "supplier_id serial PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "address VARCHAR(255) NOT NULL," +
                "contactPerson INTEGER REFERENCES employee(employee_id))";
        String product = "CREATE TABLE product(" +
                "barcode serial PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "price NUMERIC(7,2)," +
                "category VARCHAR(255)," +
                "quantity INTEGER)";
        try(Statement stmt = connection.createStatement()){
            stmt.execute(employee);
            stmt.execute(supplier);
            stmt.execute(product);
        }
    }

    public void executeQuery(String query) throws SQLException, PSQLException {
        try(Statement stmt = connection.createStatement()){
            stmt.execute(query);
        }
    }
}
