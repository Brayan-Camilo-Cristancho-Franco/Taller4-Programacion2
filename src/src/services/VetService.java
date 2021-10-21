package services;

import Model.dtos.Vet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VetService {

    private Connection conn;

    public VetService(Connection conn) {
        this.conn = conn;
    }

    public void listVets() {

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<Vet> vet = new ArrayList<Vet>();

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
                vet.add(new Vet(username, name, address, neighborhood));
            }

            // Printing results
            System.out.println("Email | Password | Role");
            for (Vet vets : vet) {
                System.out.print(vets.getUsername() + " | ");
                System.out.print(vets.getName() + " | ");
                System.out.println(vets.getAddress());
            }

            // Printing total rows
            System.out.println("Total of vets retrieved: " + vet.size() + "\n");

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
