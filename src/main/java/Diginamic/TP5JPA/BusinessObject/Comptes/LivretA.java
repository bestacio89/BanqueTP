package Diginamic.TP5JPA.BusinessObject.Comptes;

import jakarta.persistence.*;

@Entity
@Table(name = "livretA")
public class LivretA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double taux;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
