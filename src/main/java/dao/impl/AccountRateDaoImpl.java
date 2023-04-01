package main.java.dao.impl;

import db.DatabaseConnection;
import main.java.dao.AccountRateDao;
import main.java.models.AccountRate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountRateDaoImpl implements AccountRateDao {

    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(AccountRate ar) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO account_rates(account_id,cumulative_balance,rate,is_complex_rate) VALUES (?, ?, ?, ?)");
            preparedStatement.setObject(1, ar.getAccountId());
            preparedStatement.setBigDecimal(2, ar.getCumulativeBalance());
            preparedStatement.setBigDecimal(3, ar.getRate());
            preparedStatement.setBoolean(4, ar.getIsComplexRate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCumulativeBalance(UUID accountId, BigDecimal newBalance) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE account_rates SET cumulative_balance=? where account_id=?");
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setObject(2, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountRate getByAccountId(UUID accountId) {
        AccountRate ar = new AccountRate();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account_rates WHERE id=? LIMIT 1");
            preparedStatement.setObject(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ar = new AccountRate(
                        (UUID) resultSet.getObject("account_id"),
                        resultSet.getBigDecimal("cumulative_balance"),
                        resultSet.getBigDecimal("rate"),
                        resultSet.getBoolean("is_complex_rate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ar;
    }
}
