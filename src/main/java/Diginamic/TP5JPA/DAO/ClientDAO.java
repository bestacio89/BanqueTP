package Diginamic.TP5JPA.DAO;
import Diginamic.TP5JPA.BusinessObject.Client;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ClientDAO extends GenericDAO<Client, Long> {
    public ClientDAO() {
        super(Client.class);
    }
}