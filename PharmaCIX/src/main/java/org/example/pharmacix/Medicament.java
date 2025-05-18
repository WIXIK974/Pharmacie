package org.example.pharmacix;

public class Medicament {

        private int numMedicament;
        private String nom;
        private double prix;
        private String type;
        private String tableau;
        private boolean enVenteLibre;
        private int quantite;

        public Medicament(int numMedicament, String nom, double prix, String type, String tableau, boolean enVenteLibre, int quantite) {
            this.numMedicament = numMedicament;
            this.nom = nom;
            this.prix = prix;
            this.type = type;
            this.tableau = tableau;
            this.enVenteLibre = enVenteLibre;
            this.quantite = quantite;
        }

        // Getters
        public int getNumMedicament() { return numMedicament; }
        public String getNom() { return nom; }
        public double getPrix() { return prix; }
        public String getType() { return type; }
        public String getTableau() { return tableau; }
        public boolean isEnVenteLibre() { return enVenteLibre; }
        public int getQuantite() { return quantite; }

        // Setters
        public void setNumMedicament(int numMedicament) { this.numMedicament = numMedicament; }
        public void setNom(String nom) { this.nom = nom; }
        public void setPrix(double prix) { this.prix = prix; }
        public void setType(String type) { this.type = type; }
        public void setTableau(String tableau) { this.tableau = tableau; }
        public void setEnVenteLibre(boolean enVenteLibre) { this.enVenteLibre = enVenteLibre; }
        public void setQuantite(int quantite) { this.quantite = quantite; }
    }