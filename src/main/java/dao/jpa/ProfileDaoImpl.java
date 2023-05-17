package dao.jpa;

import dao.ProfileDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import models.Profile;

public class ProfileDaoImpl implements ProfileDao {
    private final EntityManager entityManager;

    public ProfileDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long create(Profile p) {
        entityManager.persist(p);
        entityManager.flush();

        return p.getId();
    }

    @Override
    public void promote(Long id, String role) {
    }

    @Override
    public Profile getById(Long id) {
        return entityManager.find(Profile.class, id);
    }

    @Override
    public Profile getByLogin(String login) {
        Query query = entityManager.createQuery("SELECT E FROM Profile E WHERE E.login = :login");
        query.setParameter("login", login);
        query.setMaxResults(1);

        return (Profile) query.getSingleResult();
    }
}
