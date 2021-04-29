package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.sql.DriverManager.*;

public class JurassicPark {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> overPopulatedDinos = new ArrayList<>();
        try (Connection connection = getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT breed " +
                        "FROM dinosaur " +
                        "WHERE actual > expected " +
                        "ORDER BY BREED ASC");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                overPopulatedDinos.add(rs.getString("breed"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return overPopulatedDinos;
    }
}