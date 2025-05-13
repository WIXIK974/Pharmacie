package org.example.pharmacix;

public class Medicament {
    private String numMedicament;
    private String nom;
    private double prix;
    private String type;
    private String tableau;
    private boolean enVenteLibre;
    private String quantite;

    public Medicament(String numMedicament,
                      String nom,
                      double prix,
                      String type,
                      String tableau,
                      boolean enVenteLibre,
                      String quantite) {
        this.numMedicament = numMedicament;
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.tableau = tableau;
        this.enVenteLibre = enVenteLibre;
        this.quantite = quantite;
    }

    // Getters
    public String getNumMedicament() { return numMedicament; }
    public String getNom()             { return nom; }
    public double getPrix()            { return prix; }
    public String getType()            { return type; }
    public String getTableau()         { return tableau; }
    public boolean isEnVenteLibre()    { return enVenteLibre; }
    public String getQuantite()        { return quantite; }

    // Setters
    public void setNumMedicament(String numMedicament) { this.numMedicament = numMedicament; }
    public void setNom(String nom)                     { this.nom = nom; }
    public void setPrix(double prix)                   { this.prix = prix; }
    public void setType(String type)                   { this.type = type; }
    public void setTableau(String tableau)             { this.tableau = tableau; }
    public void setEnVenteLibre(boolean enVenteLibre)  { this.enVenteLibre = enVenteLibre; }
    public void setQuantite(String quantite)           { this.quantite = quantite; }
}
