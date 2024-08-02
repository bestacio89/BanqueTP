package Diginamic.TP5JPA.DAO.Generic;

import Diginamic.TP5JPA.Utilities.DatabaseUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericDAO<T, ID> implements IGenericDAO<T, ID> {
    private Class<T> entityClass;
    private EntityManager entityManager;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManager = DatabaseUtil.getEntityManager(); // Use DatabaseUtil to get EntityManager
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
