package Diginamic.TP5JPA.DAO;

import Diginamic.TP5JPA.BusinessObject.AssuranceVie;
import Diginamic.TP5JPA.BusinessObject.Client;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AssuranceVieDAO extends GenericDAO<AssuranceVie, Long> {
    public AssuranceVieDAO() {
        super(AssuranceVie.class);
    }
}