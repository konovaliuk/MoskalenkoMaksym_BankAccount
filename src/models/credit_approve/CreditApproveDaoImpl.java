package models.credit_approve;

import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public CreditApprove getByAccountId(UUID accountId) {
        CreditApprove ca = new CreditApprove();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM credit_approves WHERE account_id=? LIMIT 1");
            preparedStatement.setObject(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ca = new CreditApprove(
                        (UUID) resultSet.getObject("account_id"),
                        (UUID) resultSet.getObject("approver_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }

    @Override
    public List<CreditApprove> getByApproverId(UUID approverId) {
        List<CreditApprove> creditApproves = new ArrayList<CreditApprove>();
        try {
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM credit_approves WHERE approver_id=?");
            preparedStatement.setObject(1, approverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                creditApproves.add(new CreditApprove(
                        (UUID) resultSet.getObject("account_id"),
                        (UUID) resultSet.getObject("approver_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditApproves;
    }
}
