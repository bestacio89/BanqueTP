package Diginamic.TP5JPA.BusinessObject.Comptes;

import Diginamic.TP5JPA.BusinessObject.ValueObject.Adresse;
import Diginamic.TP5JPA.BusinessObject.Banque;
import Diginamic.TP5JPA.BusinessObject.Client;
import Diginamic.TP5JPA.BusinessObject.Comptes.Operations.Operation;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;
    private double solde;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livretaid")
    private LivretA livretA;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assurancevieid")
    private AssuranceVie assuranceVie;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "numerorue", column = @Column(name = "numerorue")),
        @AttributeOverride(name = "rue", column = @Column(name = "rue")),
        @AttributeOverride(name = "codePostal", column = @Column(name = "codepostal")),
        @AttributeOverride(name = "ville", column = @Column(name = "ville"))
    })
    private Adresse adresse;

    @OneToOne(cascade = CascadeType.ALL)
    private Banque banque;

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients = new ArrayList<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LivretA getLivretA() {
        return livretA;
    }

    public void setLivretA(LivretA livretA) {
        this.livretA = livretA;
    }

    public AssuranceVie getAssuranceVie() {
        return assuranceVie;
    }

    public void setAssuranceVie(AssuranceVie assuranceVie) {
        this.assuranceVie = assuranceVie;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
