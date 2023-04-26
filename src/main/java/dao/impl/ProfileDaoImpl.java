package main.java.dao.impl;

import db.DatabaseConnection;
import main.java.dao.ProfileDao;
import main.java.models.Profile;
import main.java.types.ProfileRole;

import java.sql.*;
import java.util.UUID;

public class ProfileDaoImpl implements ProfileDao {

    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public UUID create(Profile p) {
        UUID generatedId = null;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO profiles(id,login,password_hash,role) VALUES (?, ?, ?, ?::profile_role)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, p.getId());
            preparedStatement.setString(2, p.getLogin());
            preparedStatement.setString(3, p.getPasswordHash());
            preparedStatement.setString(4, p.getRole().toSqlName());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = (UUID) generatedKeys.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public void promote(UUID id, ProfileRole role) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE profiles SET role=?::profile_role where id=?");
            preparedStatement.setString(1, role.toSqlName());
            preparedStatement.setObject(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profile getById(UUID id) {
        Profile p = new Profile();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM profiles WHERE id=? LIMIT 1");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p = new Profile(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password_hash"),
                        ProfileRole.fromSqlName(resultSet.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Profile getByLogin(String login) {
        Profile p = null;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM profiles WHERE login=? LIMIT 1");
            preparedStatement.setObject(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                p = new Profile(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password_hash"),
                        ProfileRole.fromSqlName(resultSet.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
