package dao.jpa;

import dao.TransactionDao;
import jakarta.persistence.EntityManager;
import models.Account;
import models.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    private final EntityManager entityManager;

    public TransactionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Transaction t) {
        entityManager.persist(t);
    }

    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        return Collections.singletonList(entityManager.find(Transaction.class, accountId));
    }

}
