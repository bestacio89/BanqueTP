package Diginamic.TP5JPA.DAO.Specific;

import Diginamic.TP5JPA.BusinessObject.LivretA;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;

public class LivretADAO extends GenericDAO<LivretA, Long> {
    public LivretADAO(){
        super(LivretA.class);
    }
}
