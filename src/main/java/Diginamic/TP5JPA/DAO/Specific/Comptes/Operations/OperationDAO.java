package Diginamic.TP5JPA.DAO.Specific.Comptes.Operations;

import Diginamic.TP5JPA.BusinessObject.Comptes.Operations.Operation;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;
import Diginamic.TP5JPA.Utilities.DatabaseUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OperationDAO extends GenericDAO<Operation, Long> {
    public OperationDAO(){
        super(Operation.class);
    }
    /**
      * Retrieves a list of {@link Operation} entities associated with the specified account ID.
      * @param accountId the ID of the account for which to retrieve operations.
      * @return a {@link List} of {@link Operation} entities that are associated with the given account ID.
      * If no operations are found, an empty list is returned.
      * @throws IllegalArgumentException if the provided {@code accountId} is {@code null}.
     */

    public List<Operation> findByAccountId(Long accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        EntityManager em = DatabaseUtil.getEntityManager();
        String jpql = "SELECT o FROM Operation o WHERE o.compte.id = :accountId";
        TypedQuery<Operation> query = em.createQuery(jpql, Operation.class);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }
}
