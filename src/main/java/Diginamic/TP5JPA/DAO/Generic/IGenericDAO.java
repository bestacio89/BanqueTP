package Diginamic.TP5JPA.DAO.Generic;

import java.util.List;

public interface IGenericDAO<T, ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
