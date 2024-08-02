package Diginamic.TP5JPA.DAO.Specific.Comptes;

import Diginamic.TP5JPA.BusinessObject.Comptes.LivretA;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;

public class LivretADAO extends GenericDAO<LivretA, Long> {
    public LivretADAO(){
        super(LivretA.class);
    }
}
