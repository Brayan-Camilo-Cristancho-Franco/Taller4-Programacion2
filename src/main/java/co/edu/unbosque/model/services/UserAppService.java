package co.edu.unbosque.model.services;


import co.edu.unbosque.model.dtos.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAppService {

    // Objects for handling connection
    private Connection conn;

    public UserAppService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers() {

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database

        ArrayList<UserApp> userApps = new ArrayList<UserApp>();


        try {

            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM UserApp";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String rol = rs.getString("rol");

                // Creating a new UserApp class instance and adding it to the array list
                userApps.add(new UserApp(username, email, password, rol));
            }

            // Printing results
            System.out.println(" Username | Password | Email | Role");
            for (UserApp user : userApps) {
                System.out.print(user.getUsername() + " | ");
                System.out.print(user.getPassword() + " | ");
                System.out.print(user.getEmail() + " | ");
                System.out.println(user.getRole());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + userApps.size() + "\n");

            // Closing resources
            rs.close();
            stmt.close();

        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void readForRol(String role) {
        //Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database


        ArrayList<UserApp> usersRol = new ArrayList<UserApp>();

        try {

            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM UserApp WHERE rol ='" + role + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String rol = rs.getString("rol");

                // Creating a new UserApp class instance and adding it to the array list
                usersRol.add(new UserApp(username, email, password, rol));
            }

            // Printing results
            System.out.println(" Username | Password | Email | Role");
            for (UserApp user : usersRol) {
                System.out.print(user.getUsername() + " | ");
                System.out.print(user.getPassword() + " | ");
                System.out.print(user.getEmail() + " | ");
                System.out.println(user.getRole());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + usersRol.size() + "\n");

            // Closing resources
            rs.close();
            stmt.close();

        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

}