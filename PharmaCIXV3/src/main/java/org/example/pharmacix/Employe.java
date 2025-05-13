package org.example.pharmacix;

public class Employe {
    private int idPersonne;              // Identifiant unique de la personne
    private String profession;           // Profession de l'employé
    private float salaire;               // Salaire de l'employé
    private String typeContrat;          // Type de contrat (ex : CDI, CDD, stage...)
    private String status;

    // Constructeur pour initialiser un employé avec toutes ses propriétés
    public Employe(int idPersonne, String profession, float salaire, String typeContrat, String status) {
        this.idPersonne = idPersonne;
        this.profession = profession;
        this.salaire = salaire;
        this.typeContrat = typeContrat;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

}
