package dev.club.materials.good.imports;

import java.sql.*;

public class UserRepository {

    public User findUser(String id) throws SQLException {

        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost/test",
            "user",
            "password"
        );

        PreparedStatement ps =
            connection.prepareStatement("select * from users where id = ?");

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new User(rs.getString("email"));
        }

        connection.close();

        return null;
    }
}
