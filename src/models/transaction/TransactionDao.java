package models.transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionDao {
    void create(Transaction t);

    List<Transaction> getByAccountId(UUID accountId);

}
