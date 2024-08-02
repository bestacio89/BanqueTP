package Diginamic.TP5JPA.DAO.Specific;

import Diginamic.TP5JPA.BusinessObject.Banque;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;

public class BanqueDAO extends GenericDAO<Banque, Long> {
   public BanqueDAO(){
        super(Banque.class);
    }
}
