import Model.dtos.Owner;
import services.OwnersService;
import services.UsersService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/Pets";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "";

    public static void main(String[] args) {

        // Objects for handling connection
        Connection conn = null;

        try {

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            UsersService usersService = new UsersService(conn);
            usersService.listUsers("owner");

           /* PetsService petsService = new PetsService(conn);
            petsService.countBySpecies("dog");

            OwnersService ownersService = new OwnersService(conn);
           // ownersService.updateOwner(new Owner("PEPE", "52", "Pepito Perez","calle 55","solo bosa"));

            // Closing database connection
            conn.close();*/

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch(ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
