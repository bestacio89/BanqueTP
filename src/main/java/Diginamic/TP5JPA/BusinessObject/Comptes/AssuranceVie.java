package Diginamic.TP5JPA.BusinessObject.Comptes;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assuranceVie")
public class AssuranceVie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dateFin")
    private LocalDate dateFin;
    private double taux;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
