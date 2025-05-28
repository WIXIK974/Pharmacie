package org.example.pharmacix;

import java.util.Date;

public class Vente {
    private int numVente;
    private Date dateVente;
    private String fk_numeroSS;
    private String fk_numEmploye;
    private String fk_numMedicament;
    private int fk_numOrdonnance;
    private String quantiteVendue;

    public Vente(int numVente, Date dateVente, String fk_numeroSS, String fk_numEmploye, String fk_numMedicament, int fk_numOrdonnance, String quantiteVendue) {
        this.numVente = numVente;
        this.dateVente = dateVente != null ? dateVente : new Date(); // Assurez-vous que la date de vente est initialisée
        this.fk_numeroSS = fk_numeroSS;
        this.fk_numEmploye = fk_numEmploye;
        this.fk_numMedicament = fk_numMedicament;
        this.fk_numOrdonnance = fk_numOrdonnance;
        this.quantiteVendue = quantiteVendue;
    }

    public int getNumVente() { return numVente; }
    public void setNumVente(int numVente) { this.numVente = numVente; }

    public Date getDateVente() { return dateVente; }
    public void setDateVente(Date dateVente) { this.dateVente = dateVente; }

    public String getFk_numeroSS() { return fk_numeroSS; }
    public void setFk_numeroSS(String fk_numeroSS) { this.fk_numeroSS = fk_numeroSS; }

    public String getFk_numEmploye() { return fk_numEmploye; }
    public void setFk_numEmploye(String fk_numEmploye) { this.fk_numEmploye = fk_numEmploye; }

    public String getFk_numMedicament() { return fk_numMedicament; }
    public void setFk_numMedicament(String fk_numMedicament) { this.fk_numMedicament = fk_numMedicament; }

    public int getFk_numOrdonnance() { return fk_numOrdonnance; }
    public void setFk_numOrdonnance(int fk_numOrdonnance) { this.fk_numOrdonnance = fk_numOrdonnance; }

    public String getQuantiteVendue() { return quantiteVendue; }
    public void setQuantiteVendue(String quantiteVendue) { this.quantiteVendue = quantiteVendue; }
}
