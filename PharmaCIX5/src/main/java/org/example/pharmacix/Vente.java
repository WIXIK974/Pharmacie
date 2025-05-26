package org.example.pharmacix;

import java.util.Date;

public class Vente {
    private int numVente;
    private Date dateVente;
    private String fk_numeroSS;
    private int fk_numEmploye;
    private String fk_numMedicament;
    private int fk_numOrdonnance;

    public Vente(int numVente, Date dateVente, String fk_numeroSS, int fk_numEmploye, String fk_numMedicament, int fk_numOrdonnance) {
        this.numVente = numVente;
        this.dateVente = dateVente;
        this.fk_numeroSS = fk_numeroSS;
        this.fk_numEmploye = fk_numEmploye;
        this.fk_numMedicament = fk_numMedicament;
        this.fk_numOrdonnance = fk_numOrdonnance;
    }

    public int getNumVente() { return numVente; }
    public void setNumVente(int numVente) { this.numVente = numVente; }

    public Date getDateVente() { return dateVente; }

    public String getFk_numeroSS() { return fk_numeroSS; }

    public int getFk_numEmploye() { return fk_numEmploye; }

    public String getFk_numMedicament() { return fk_numMedicament; }

    public int getFk_numOrdonnance() { return fk_numOrdonnance; }
}