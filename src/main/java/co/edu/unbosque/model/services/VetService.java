package co.edu.unbosque.model.services;

import co.edu.unbosque.model.dtos.UserApp;
import co.edu.unbosque.model.dtos.Vet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VetService {
    // Objects for handling connection
    private Connection conn;

    public VetService(Connection conn) {
        this.conn = conn;
    }

    public void listVets() {

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database

        ArrayList<Vet> Vets = new ArrayList<Vet>();


        try {

            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Vet";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String neighborhood = rs.getString("neighborhood");

                // Creating a new UserApp class instance and adding it to the array list
                Vets.add(new Vet(username, name, address, neighborhood));
            }

            // Printing results
            System.out.println(" Username | Name | Address | Neighborhood");
            for (Vet user : Vets) {
                System.out.print(user.getUsername() + " | ");
                System.out.print(user.getName() + " | ");
                System.out.print(user.getAddress() + " | ");
                System.out.println(user.getNeighborhood());
            }

            // Printing total rows
            System.out.println("Total of veterinary registered: " + Vets.size() + "\n");

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
