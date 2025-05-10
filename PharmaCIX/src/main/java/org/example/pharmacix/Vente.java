package org.example.pharmacix;

import java.util.Date;

public class Vente {
        // Attributs d’un des ventes
        private int numVente;
        private Date dateVente;
        private String fk_numeroSS;
        private String fk_numEmploye;
        private String fk_numMedicament;
        private Integer fk_numOrdonnance;

        // Constructeur pour initialiser un médicament avec toutes ses propriétés
        public Vente(int numVente, Date dateVente, String fk_numeroSS, String fk_numEmploye, String fk_numMedicament, int fk_numOrdonnance) {
            this.numVente= numVente;
            this.dateVente = dateVente;
            this.fk_numeroSS = fk_numeroSS;
            this.fk_numEmploye = fk_numEmploye;
            this.fk_numMedicament = fk_numMedicament;
            this.fk_numOrdonnance = fk_numOrdonnance;
        }

        // Getters (accesseurs) pour permettre à JavaFX de lire les données à afficher dans la table
        public int getNumVente() {
            return numVente;
        }

        public Date getDateVente() {
            return dateVente;
        }

    public String getFk_numeroSS() {
        return fk_numeroSS;
    }

    public String getFk_numEmploye() {
        return fk_numEmploye;
    }

    public String getFk_numMedicament() {
        return fk_numMedicament;
    }

    public Integer getFk_numOrdonnance() {
        return fk_numOrdonnance;
    }
    }


