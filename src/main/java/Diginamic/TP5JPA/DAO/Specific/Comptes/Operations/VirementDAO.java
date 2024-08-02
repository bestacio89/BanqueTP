package Diginamic.TP5JPA.DAO.Specific.Comptes.Operations;

import Diginamic.TP5JPA.BusinessObject.Comptes.Operations.Virement;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;

public class VirementDAO extends GenericDAO<Virement, Long> {

    public VirementDAO(){
        super(Virement.class);
    }
}

