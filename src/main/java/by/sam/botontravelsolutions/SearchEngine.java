package by.sam.botontravelsolutions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchEngine {
    private static ResultSet rs;
    private String name;
    private String description;
    private static Connection connection= MySQL.getConnection();
    public void findCity() {

    }

    public static void main(String[] args) {
        Config.loadConfig();
        String query = "SELECT * FROM City WHERE Name='Minsk'";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(query);


            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int year = rs.getInt(2);
                String name = rs.getString(1);
                String descr = rs.getString(3);
                System.out.printf("year: %d, name: %s, descr: %s %n", year, name, descr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
