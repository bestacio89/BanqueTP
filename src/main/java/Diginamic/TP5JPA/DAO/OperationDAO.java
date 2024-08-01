package Diginamic.TP5JPA.DAO;

import Diginamic.TP5JPA.BusinessObject.Operation;

public class OperationDAO extends GenericDAO<Operation, Long> {
    public OperationDAO(){
        super(Operation.class);
    }
}
