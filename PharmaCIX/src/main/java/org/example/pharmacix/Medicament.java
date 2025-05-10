package org.example.pharmacix;

/**
 * Cette classe représente un médicament avec ses différentes propriétés.
 */
public class Medicament {
    // Attributs d’un médicament
    private String nom;                   // Nom du médicament
    private String numMedicament;        // Numéro d’identification du médicament
    private String tableau;              // Tableau auquel appartient le médicament (ex : stupéfiant, etc.)
    private float prix;                  // Prix du médicament
    private String quantiteParUnite;     // Quantité par unité (ex : 500mg, 250ml...)
    private String type;                 // Type du médicament (ex : comprimé, sirop, etc.)
    private boolean enVenteLibre;        // Indique si le médicament est en vente libre ou non

    // Constructeur pour initialiser un médicament avec toutes ses propriétés
    public Medicament(String nom, String numMedicament, String tableau, float prix, String quantiteParUnite, String type, boolean enVenteLibre) {
        this.nom = nom;
        this.numMedicament = numMedicament;
        this.tableau = tableau;
        this.prix = prix;
        this.quantiteParUnite = quantiteParUnite;
        this.type = type;
        this.enVenteLibre = enVenteLibre;
    }

    // Getters (accesseurs) pour permettre à JavaFX de lire les données à afficher dans la table
    public String getNom() {
        return nom;
    }

    public String getNumMedicament() {
        return numMedicament;
    }

    public String getTableau() {
        return tableau;
    }

    public float getPrix() {
        return prix;
    }

    public String getQuantiteParUnite() {
        return quantiteParUnite;
    }

    public String getType() {
        return type;
    }

    public boolean isEnVenteLibre() {
        return enVenteLibre;
    }
}
