package org.example.pharmacix;

public class Employe {
    private int idPersonne;              // Identifiant unique de la personne
    private String profession;           // Profession de l'employé
    private float salaire;               // Salaire de l'employé
    private String typeContrat;          // Type de contrat (ex : CDI, CDD, stage...)
    private int status;
    private String identifiant;          // Identifiant de l'employé
    private String password;             // Mot de passe de l'employé

    // Constructeur pour initialiser un employé avec toutes ses propriétés
    public Employe(int idPersonne, String profession, float salaire, String typeContrat, int status) {
        this.idPersonne = idPersonne;
        this.profession = profession;
        this.salaire = salaire;
        this.typeContrat = typeContrat;
        this.status = status;
        this.identifiant = identifiant;
        this.password = password;
    }

    // Getters (accesseurs) pour JavaFX ou autres frameworks de liaison de données
    public int getIdPersonne() {
        return idPersonne;
    }

    public String getProfession() {
        return profession;
    }

    public float getSalaire() {
        return salaire;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public int getStatus() {
        return status;
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
}
