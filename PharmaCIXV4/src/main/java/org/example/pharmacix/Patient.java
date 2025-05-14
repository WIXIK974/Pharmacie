package org.example.pharmacix;

import java.math.BigDecimal;
import java.sql.Date;

public class Patient extends Personne {
    private String numeroSS;
    private String mutuelle;
    private BigDecimal taille;
    private BigDecimal poids;
    private Character sexe;
    private String fkMedecin;
    private int status;

    public Patient(int idPersonne, String nom, String prenom, Date dateNaissance, String adresse, String numeroTel, String type,
                   String numeroSS, String mutuelle, BigDecimal taille, BigDecimal poids, Character sexe, String fkMedecin, int status) {
        super(idPersonne, nom, prenom, dateNaissance, adresse, numeroTel, type);
        this.numeroSS = numeroSS;
        this.mutuelle = mutuelle;
        this.taille = taille;
        this.poids = poids;
        this.sexe = sexe;
        this.fkMedecin = fkMedecin;
        this.status = status;
    }

    // Getters et setters spécifiques à Patient
    public String getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(String numeroSS) {
        this.numeroSS = numeroSS;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    public BigDecimal getTaille() {
        return taille;
    }

    public void setTaille(BigDecimal taille) {
        this.taille = taille;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public String getFkMedecin() {
        return fkMedecin;
    }

    public void setFkMedecin(String fkMedecin) {
        this.fkMedecin = fkMedecin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
