package Diginamic.TP5JPA.BusinessObject;

import Diginamic.TP5JPA.BusinessObject.Comptes.Compte;
import Diginamic.TP5JPA.BusinessObject.ValueObject.Adresse;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "numerorue", column = @Column(name = "adresse_numerorue")),
        @AttributeOverride(name = "rue", column = @Column(name = "adresse_rue")),
        @AttributeOverride(name = "codePostal", column = @Column(name = "adresse_codepostal")),
        @AttributeOverride(name = "ville", column = @Column(name = "adresse_ville"))
    })
    private Adresse adresse;

    @ManyToMany
    @JoinTable(
        name = "client_compte",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "compte_id")
    )
    private List<Compte> comptes = new ArrayList<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
