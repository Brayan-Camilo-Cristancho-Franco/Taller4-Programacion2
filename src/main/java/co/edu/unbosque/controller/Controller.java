package co.edu.unbosque.controller;


import co.edu.unbosque.model.services.VetService;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Controller {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "5u8e2u3u";

    public Controller() {
        funcionar();
    }

    public void funcionar() {

        // Objects for handling connection
        Connection conn = null;

        try {

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            /*
            System.out.println("Ingrese el rol de los usuarios a buscar");
            UserAppService usersService = new UserAppService(conn);
            Scanner sc = new Scanner(System.in);
            String rol = sc.nextLine();
            usersService.readForRol(rol);*/

            VetService vetService = new VetService(conn);
            vetService.listVets();


/*
            PetsService petsService = new PetsService(conn);
            petsService.countBySpecies("dog");

            OwnersService ownersService = new OwnersService(conn);
            ownersService.updateOwner(new Owner(6698, null, "Pepito Perez"));
*/
            // Closing database connection
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
