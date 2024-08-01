package Diginamic.TP5JPA.DAO;


import Diginamic.TP5JPA.BusinessObject.Compte;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompteDAO extends GenericDAO<Compte, Long> {
    public CompteDAO() {
        super(Compte.class);
    }
}