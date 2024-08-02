package Diginamic.TP5JPA.DAO.Specific;

import Diginamic.TP5JPA.BusinessObject.Virement;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;

public class VirementDAO extends GenericDAO<Virement, Long> {

    public VirementDAO(){
        super(Virement.class);
    }
}

