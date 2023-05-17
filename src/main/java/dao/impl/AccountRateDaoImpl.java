package dao.impl;

import db.DatabaseConnection;
import dao.AccountRateDao;
import models.AccountRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountRateDaoImpl implements AccountRateDao {

    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(AccountRate ar) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO account_rates(account_id,rate,initial_balance,payment_frequency,payments_processed,next_payment_at) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setObject(1, ar.getAccountId());
            preparedStatement.setBigDecimal(2, ar.getRate());
            preparedStatement.setBigDecimal(3, ar.getInitialBalance());
            preparedStatement.setInt(4, ar.getPaymentFrequency());
            preparedStatement.setInt(5, ar.getPaymentsProcessed());
            preparedStatement.setTimestamp(6, ar.getNextPaymentAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountRate getByAccountId(Long accountId) {
        AccountRate ar = new AccountRate();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account_rates WHERE id=? LIMIT 1");
            preparedStatement.setObject(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ar = new AccountRate(
                        (Long) resultSet.getObject("account_id"),
                        resultSet.getBigDecimal("rate"),
                        resultSet.getBigDecimal("initial_balance"),
                        resultSet.getInt("payment_frequency"),
                        resultSet.getInt("payments_processed"),
                        resultSet.getTimestamp("next_payment_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ar;
    }
}
