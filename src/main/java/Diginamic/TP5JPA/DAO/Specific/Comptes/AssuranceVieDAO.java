package Diginamic.TP5JPA.DAO.Specific.Comptes;


import Diginamic.TP5JPA.BusinessObject.Comptes.AssuranceVie;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AssuranceVieDAO extends GenericDAO<AssuranceVie, Long> {
    public AssuranceVieDAO() {
        super(AssuranceVie.class);
    }
}