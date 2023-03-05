package models.profile;

import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProfileDaoImpl implements ProfileDao {

    private static final Connection connection = DatabaseConnection.getConnection();

    public static void create(Profile p) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO profiles(id,login,password_hash,role) VALUES (?, ?, ?, ?::profile_role)");
            preparedStatement.setObject(1, p.getId());
            preparedStatement.setString(2, p.getLogin());
            preparedStatement.setString(3, p.getPasswordHash());
            preparedStatement.setString(4, p.getRole().toSqlName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateById(Profile p) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE profiles SET login=?, password_hash=?, role=?::profile_role where id=?");
            preparedStatement.setString(1, p.getLogin());
            preparedStatement.setString(2, p.getPasswordHash());
            preparedStatement.setString(3, p.getRole().toSqlName());
            preparedStatement.setObject(4, p.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Profile getById(UUID id) {
        Profile p = new Profile();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM profiles WHERE id=? LIMIT 1");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p = new Profile(
                        (UUID)resultSet.getObject("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password_hash"),
                        ProfileRole.sqlToValue.get(resultSet.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
