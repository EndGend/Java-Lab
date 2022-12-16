package lab5;

import org.postgresql.util.PSQLException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.sql.Statement;

public class Lab5Test {
    @Test(expectedExceptions = SQLException.class)
    public void connectionTest() throws SQLException{
        DatabaseService db = new DatabaseService("jdbc:postgresql://localhost:5432/javadb", "postgreGADSGDSs", "rootzxc");
        db.connect();
    }

    @Test(expectedExceptions = PSQLException.class)
    public void creationTest() throws SQLException{
        DatabaseService db = new DatabaseService("jdbc:postgresql://localhost:5432/javadb", "postgres", "rootzxc");
        db.connect();
        String query = "CREATE TABLE employee2" +
                "(employee_id serial PRIMARY KEY" +
                "name VARCHAR(255) NOT NULL" +
                "salary NUMERIC(7,1) NOT NULL)" +
                "address VARCHAR(255) NOT NULL," +
                "phoneNumber VARCHAR(12) UNIQUE," +
                "isInsuranced BOOLEAN)";
        db.executeQuery(query);
    }
}
