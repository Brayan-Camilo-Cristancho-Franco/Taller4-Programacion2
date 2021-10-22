package co.edu.unbosque.model.services;


import co.edu.unbosque.model.dtos.UserApp;
import co.edu.unbosque.model.dtos.Vet;
import co.edu.unbosque.model.dtos.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class VisitService {

    // Objects for handling connection
    private Connection conn;
    private String pet_id;


    public VisitService(Connection conn) {
        this.conn = conn;
    }


    public void updateDateVisit(Visit visits) {
        Scanner sc = new Scanner(System.in);

        //System.out.println(" Ingrese el pet_id de la mascotoa para realizar la consulta");
        // pet_id = sc.nextLine();
        System.out.println(pet_id + "pet id");
        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            stmt = conn.createStatement();
            String sql = "UPDATE visit SET created_at = '" + visits.getCreated_at() + "' WHERE pet_id = '" + visits.getPet_id() + "'";
            System.out.println(sql);
            int rowsUpdated = stmt.executeUpdate(sql);

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

    public void readForRol() {
        //Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database

        Scanner sc = new Scanner(System.in);
        ArrayList<Visit> visits = new ArrayList<Visit>();
        System.out.println(" Ingrese el rol ");
        String rol = sc.nextLine();

        try {
            Visit visitdto = new Visit(null, null, null, null, null, null);

            // Executing a SQL query
            System.out.println("=> Updating owner...");
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS count FROM userapp WHERE rol = '" + rol + "'";
            System.out.println(sql);


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
        try {


            // Executing a SQL query
            System.out.println("=> Listing visits...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM visit WHERE pet_id ='" + pet_id + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String visit_id = rs.getString("visit_id");
                String created_at = rs.getString("created_at");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String vet_id = rs.getString("vet_id");
                String pet_ids = rs.getString("pet_id");


                // Creating a new Vet class instance and adding it to the array list
                visits.add(new Visit(visit_id, created_at, type, description, vet_id, pet_ids));
            }

            // Printing results
            System.out.println(" Username | Password | Email | Role");
            for (Visit vis : visits) {
                System.out.print(vis.getVisit_id() + " | ");
                System.out.print(vis.getCreated_at() + " | ");
                System.out.print(vis.getType() + " | ");
                System.out.print(vis.getDescription() + " | ");
                System.out.print(vis.getVet_id() + " | ");
                System.out.println(vis.getPet_id() + " | ");
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + visits.size() + "\n");

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

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }
}