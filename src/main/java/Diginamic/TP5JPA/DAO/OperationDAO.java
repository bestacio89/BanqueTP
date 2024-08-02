package Diginamic.TP5JPA.DAO;

import Diginamic.TP5JPA.BusinessObject.Operation;
import Diginamic.TP5JPA.Utilities.DatabaseUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OperationDAO extends GenericDAO<Operation, Long> {
    public OperationDAO(){
        super(Operation.class);
    }

        public List<Operation> findByAccountId(Long accountId) {
        EntityManager em = DatabaseUtil.getEntityManager();
        String jpql = "SELECT o FROM Operation o WHERE o.compte.id = :accountId";
        TypedQuery<Operation> query = em.createQuery(jpql, Operation.class);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }
}
