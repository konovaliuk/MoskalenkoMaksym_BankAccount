package main.java.dao.impl;

import db.DatabaseConnection;
import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.types.AccountStatus;
import main.java.types.AccountType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountDaoImpl implements AccountDao {
    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(Account a) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO accounts(id,profile_id,type,balance,status,expired_at,opened_at,closed_at) " +
                            "VALUES (?, ?, ?::account_type, ?, ?::account_status, ?, ?, ?)");
            preparedStatement.setObject(1, a.getId());
            preparedStatement.setObject(2, a.getProfileId());
            preparedStatement.setString(3, a.getType().toSqlName());
            preparedStatement.setBigDecimal(4, a.getBalance());
            preparedStatement.setString(5, a.getStatus().toSqlName());
            preparedStatement.setTime(6, a.getExpiredAt());
            preparedStatement.setTime(7, a.getOpenedAt());
            preparedStatement.setTime(8, a.getClosedAt());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openAccount(UUID id) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE accounts SET status=?::account_status, opened_at=? where id=?");
            preparedStatement.setString(1, AccountStatus.Open.toSqlName());
            preparedStatement.setTime(2, new Time(System.currentTimeMillis()));
            preparedStatement.setObject(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeAccount(UUID id) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE accounts SET status=?::account_status, closed_at=? where id=?");
            preparedStatement.setString(1, AccountStatus.Closed.toSqlName());
            preparedStatement.setTime(2, new Time(System.currentTimeMillis()));
            preparedStatement.setObject(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getById(UUID id) {
        Account a = new Account();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id=? LIMIT 1");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                a = new Account(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("profile_id"),
                        AccountType.fromSqlName(resultSet.getString("type")),
                        resultSet.getBigDecimal("balance"),
                        AccountStatus.fromSqlName(resultSet.getString("status")),
                        resultSet.getTime("expired_at"),
                        resultSet.getTime("opened_at"),
                        resultSet.getTime("closed_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public List<Account> getByProfileId(UUID profileId) {
        List<Account> accounts = new ArrayList<Account>();
        try {
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE profile_id=?");
            preparedStatement.setObject(1, profileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accounts.add(new Account(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("profile_id"),
                        AccountType.fromSqlName(resultSet.getString("type")),
                        resultSet.getBigDecimal("balance"),
                        AccountStatus.fromSqlName(resultSet.getString("status")),
                        resultSet.getTime("expired_at"),
                        resultSet.getTime("opened_at"),
                        resultSet.getTime("closed_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
