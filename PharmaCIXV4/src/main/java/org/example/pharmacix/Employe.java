package org.example.pharmacix;

public class Employe {
    private int idPersonne;
    private String numEmploye;
    private String profession;
    private float salaire;
    private String typeContrat;
    private String identifiant;
    private String password;
    private int status;

    public Employe(int idPersonne, String numEmploye, float salaire, String typeContrat, int status) {
        this.idPersonne = idPersonne;
        this.numEmploye = numEmploye;
        this.profession = profession;
        this.salaire = salaire;
        this.typeContrat = typeContrat;
        this.identifiant = identifiant;
        this.password = password;
        this.status = status;
    }

    public Employe(int idPersonne, String identifiant, int statusInt, String profession, float salaireFloat, String typeContrat) {
    }

    // Getters et setters
    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(String numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
