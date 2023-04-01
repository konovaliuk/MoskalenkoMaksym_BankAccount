package main.java.dao.impl;

import db.DatabaseConnection;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;
import main.java.types.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionDaoImpl implements TransactionDao {
    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(Transaction t) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO account_rates(id,src_account,dst_account,volume,type,executed_at) VALUES (?, ?, ?, ?, ?::transaction_type, ?)");
            preparedStatement.setObject(1, t.getId());
            preparedStatement.setObject(2, t.getSourceAccount());
            preparedStatement.setObject(3, t.getDestinationAccount());
            preparedStatement.setBigDecimal(4, t.getVolume());
            preparedStatement.setString(5, t.getType().toSqlName());
            preparedStatement.setTimestamp(6, t.getExecutedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getByAccountId(UUID accountId) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transactions WHERE src_account=? OR dst_account=?");
            preparedStatement.setObject(1, accountId);
            preparedStatement.setObject(2, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(new Transaction(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("src_account"),
                        (UUID) resultSet.getObject("dst_account"),
                        resultSet.getBigDecimal("volume"),
                        TransactionType.fromSqlName(resultSet.getString("type")),
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
