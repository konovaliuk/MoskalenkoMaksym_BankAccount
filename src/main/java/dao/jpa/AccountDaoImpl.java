package dao.jpa;

import jakarta.persistence.EntityManager;
import dao.AccountDao;
import jakarta.persistence.Query;
import models.Account;
import types.AccountStatus;

import java.math.BigDecimal;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private final EntityManager entityManager;

    public AccountDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Account a) {
        entityManager.persist(a);
    }

    @Override
    public void openAccount(Long id) {
        Account a = new Account();
        a.setId(id);
        a.setStatus(AccountStatus.Open.toSqlName());

        entityManager.merge(a);
    }

    @Override
    public void closeAccount(Long id) {
        Account a = new Account();
        a.setId(id);
        a.setStatus(AccountStatus.Closed.toSqlName());

        entityManager.merge(a);
    }

    @Override
    public Account getById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> getByProfileId(Long profileId) {
        Query query = entityManager.createQuery("SELECT E FROM Account E WHERE E.profile_id = :profile_id");
        query.setParameter("profile_id", profileId);

        return query.getResultList();
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        Query query = entityManager.createQuery("SELECT E FROM Account E WHERE E.accountNumber = :account_number");
        query.setParameter("account_number", accountNumber);
        query.setMaxResults(1);

        return (Account) query.getSingleResult();
    }

    @Override
    public Account getDefaultAccountByProfileId(Long profileId) {
        Query query = entityManager.createQuery("SELECT E FROM Account E WHERE E.profile_id = :profile_id AND type='default'::account_type");
        query.setParameter("profile_id", profileId);
        query.setMaxResults(1);

        return (Account) query.getSingleResult();
    }

    @Override
    public List<Account> getProcessingCreditRequests() {
        Query query = entityManager.createQuery("SELECT E FROM Account E WHERE type='credit'::account_type AND status='processing'::account_status");

        return query.getResultList();
    }

    @Override
    public void updateBalance(Long id, BigDecimal newBalance) {
        Account a = new Account();
        a.setId(id);
        a.setBalance(newBalance);

        entityManager.merge(a);
    }

    @Override
    public void update(Account a) {
        entityManager.merge(a);
    }
}
