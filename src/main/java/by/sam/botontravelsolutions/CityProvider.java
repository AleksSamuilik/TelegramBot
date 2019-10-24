package by.sam.botontravelsolutions;

import java.sql.*;

public class CityProvider implements OpertaionProvider, RequestProvider {
    private String nameCity;
    private ResultSet rs;
    private  String name;
    private  int year;
    private  String description;


    @Override
    public String getNameOperation() {
        return "/city";
    }

    @Override
    public String getOperation() {
        String description = requestDB(nameCity);
        if (name != null) {
            return description;
        } else {
            return "I don’t have such a city.";
        }
    }

    @Override
    public void setAttribute(String nameCity) {
        this.nameCity = nameCity;
    }

    @Override
    public String getOperationDescription() {
        return "Get a description of the city. Like this, for example: /city Minsk";
    }

    @Override
    public String requestDB(String city) {
        String query = "SELECT * FROM City WHERE Name='" + city + "'";
        Statement stmt;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7309426", "sql7309426", "Pf3yT4C76b")) {
            System.out.println("Database connected!");
            stmt = connection.createStatement();
            stmt.execute(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                year = rs.getInt(2);
                name = rs.getString(1);
                description = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name + " was created in " + year + ". " + description;

    }
}