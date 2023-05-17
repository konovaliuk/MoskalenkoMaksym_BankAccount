package dao.impl;

import dao.CreditApproveDao;
import db.DatabaseConnection;
import models.CreditApprove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CreditApproveDaoImpl implements CreditApproveDao {
    private static final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(CreditApprove ca) {
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO credit_approves(account_id,approver_id) VALUES (?, ?)");
            preparedStatement.setObject(1, ca.getAccountId());
            preparedStatement.setObject(2, ca.getApproverId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CreditApprove getByAccountId(Long accountId) {
        CreditApprove ca = new CreditApprove();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM credit_approves WHERE account_id=? LIMIT 1");
            preparedStatement.setObject(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ca = new CreditApprove(
                        (Long) resultSet.getObject("account_id"),
                        (Long) resultSet.getObject("approver_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }
}
