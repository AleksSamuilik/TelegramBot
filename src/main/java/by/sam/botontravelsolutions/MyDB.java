package by.sam.botontravelsolutions;

import java.sql.*;


public class MyDB {
    private static String url = Config.DB_URL;
    private static String username = Config.DB_USER;
    private static String password = Config.DB_PWD;

    public static void loadMyDB() {
        url = Config.DB_URL;
        username = Config.DB_USER;
        password = Config.DB_PWD;
        System.out.println("Test connecting database...");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection check was successful. Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return url;
    }

    public static String getPassword() {
        return url;
    }
}