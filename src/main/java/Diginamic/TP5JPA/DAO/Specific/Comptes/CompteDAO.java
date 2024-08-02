package Diginamic.TP5JPA.DAO.Specific.Comptes;


import Diginamic.TP5JPA.BusinessObject.Comptes.Compte;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompteDAO extends GenericDAO<Compte, Long> {
    public CompteDAO() {
        super(Compte.class);
    }
}