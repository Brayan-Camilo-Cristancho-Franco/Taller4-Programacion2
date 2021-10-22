package co.edu.unbosque.model.services;


import co.edu.unbosque.model.dtos.PetCase;


import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;


public class PetCaseService {


    // Objects for handling connection
    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void insertNewPetCase(PetCase petcase) {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Updating owner...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO petcase(created_at,type,description,pet_id) VALUES ( '" + petcase.getCreated_at() + "','" + petcase.getType() + "','" + petcase.getDescription() + "','" + petcase.getPet_id() + "')";
            System.out.println(sql);
            int rowsInsert = stmt.executeUpdate(sql);

            // Printing results
            System.out.println("Rows Inserted: " + rowsInsert + "\n");

            // Closing resources
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
