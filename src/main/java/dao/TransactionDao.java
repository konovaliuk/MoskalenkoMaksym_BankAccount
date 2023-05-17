package dao;

import models.Transaction;

import java.util.List;


public interface TransactionDao {
    void create(Transaction t);

    List<Transaction> getByAccountId(Long accountId);

}
