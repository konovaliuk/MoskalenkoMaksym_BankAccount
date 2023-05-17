package dao.impl;

import db.DatabaseConnection;
import dao.TransactionDao;
import models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TransactionDaoImpl implements TransactionDao {
    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(Transaction t) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO transactions(id,src_account,dst_account,volume,type,executed_at) VALUES (?, ?, ?, ?, ?::transaction_type, ?)");
            preparedStatement.setObject(1, t.getId());
            preparedStatement.setObject(2, t.getSourceAccount());
            preparedStatement.setObject(3, t.getDestinationAccount());
            preparedStatement.setBigDecimal(4, t.getVolume());
            preparedStatement.setString(5, t.getType());
            preparedStatement.setTimestamp(6, t.getExecutedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transactions WHERE src_account=? OR dst_account=?");
            preparedStatement.setObject(1, accountId);
            preparedStatement.setObject(2, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(new Transaction(
                        (Long) resultSet.getObject("id"),
                        (Long) resultSet.getObject("src_account"),
                        (Long) resultSet.getObject("dst_account"),
                        resultSet.getBigDecimal("volume"),
                        resultSet.getString("type"),
                        resultSet.getTimestamp("executed_at"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getTimestamp("updated_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
