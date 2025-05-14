package org.example.pharmacix;

import java.util.Date;

public class Medecin extends Personne {
    private String numeroRPPS;
    private String specialite;
    private int status;

    public Medecin(int idPersonne, String nom, String prenom, Date dateNaissance, String adresse, String numeroTel, String type,
                   String numeroRPPS, String specialite, int status) {
        super(idPersonne, nom, prenom, dateNaissance, adresse, numeroTel, type);
        this.numeroRPPS = numeroRPPS;
        this.specialite = specialite;
        this.status = status;
    }

    // Getters et setters spécifiques à Medecin
    public String getNumeroRPPS() {
        return numeroRPPS;
    }

    public void setNumeroRPPS(String numeroRPPS) {
        this.numeroRPPS = numeroRPPS;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
