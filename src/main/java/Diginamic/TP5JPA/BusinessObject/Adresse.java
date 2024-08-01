package Diginamic.TP5JPA.BusinessObject;

import jakarta.persistence.*;

@Embeddable
public class Adresse {


    private int numerorue;
    private String rue;
    private int codePostal;
    private String ville;

    // Getters and Setters


    public int getNumerorue() {
        return numerorue;
    }

    public void setNumero(int numero) {
        this.numerorue = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
