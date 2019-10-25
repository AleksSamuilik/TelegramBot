package by.sam.botontravelsolutions;

import java.sql.*;

public class CityProvider implements CommandProvider {

    private String nameCity;
    private boolean isSuchCity;


    @Override
    public String getNameCommand() {
        return "/city";
    }

    @Override
    public String executeCommand() {
        String descriptionCity = findCity(nameCity);
        if (isSuchCity){
            return descriptionCity;
        } else {
            return "I don’t have such a city.";
        }
    }

    @Override
    public void setAttribute(String nameCity) {
        this.nameCity = nameCity;
    }

    @Override
    public String getDescriptionCommand() {
        return "Get a description of the city. Like this, for example: /city Minsk";
    }


    public String findCity(String city) {
        String query = "SELECT * FROM City WHERE Name= ?";
        PreparedStatement stmt;
        String name="";
        int year=0;
        String description="";
        try (Connection connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PWD)) {
            System.out.println("Database connected!");
            stmt = connection.prepareStatement(query);
            stmt.setString(1,city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                year = rs.getInt(2);
                name = rs.getString(1);
                description = rs.getString(3);
            }
            isSuchCity = year!=0&&name!=null&&description!=null? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name + " was created in " + year + ". " + description;
    }
}