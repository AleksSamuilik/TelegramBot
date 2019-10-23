package by.sam.botontravelsolutions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Javin Paul
 */
public class JavaToMySQL {
    private static ResultSet rs;



    public static void main(String[] args) {
        String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7309426";
        String username = "sql7309426";
        String password = "Pf3yT4C76b";
        String query = "SELECT * FROM City";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            Statement stmt = connection.createStatement();
            stmt.execute(query);


            // executing SELECT query
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