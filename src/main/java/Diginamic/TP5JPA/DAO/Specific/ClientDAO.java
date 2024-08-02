package Diginamic.TP5JPA.DAO.Specific;
import Diginamic.TP5JPA.BusinessObject.Client;
import Diginamic.TP5JPA.DAO.Generic.GenericDAO;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ClientDAO extends GenericDAO<Client, Long> {
    public ClientDAO() {
        super(Client.class);
    }
}