package Diginamic.TP5JPA.BusinessObject;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private double montant;
    private String motif;

    @ManyToOne
    private Compte compte;

    @OneToOne(cascade = CascadeType.ALL)
    private Virement virement;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Virement getVirement() {
        return virement;
    }

    public void setVirement(Virement virement) {
        this.virement = virement;
    }
}
