package services;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersService {

    // Objects for handling connection
    private Connection conn;

    public UsersService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers(String rol) {

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        //List<UserApp> userApps = new ArrayList<UserApp>();

        try {

            // Executing a SQL query
            System.out.println("=> Counting users by rol...");
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS count FROM userapp WHERE rol  = '" + rol + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Pointing to fist row
            rs.next();

            // Printing results
            System.out.println("Total of pets by species: " + rs.getInt("count") + "\n");

            // Closing resources
            rs.close();
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
