package org.example.pharmacix;

import java.sql.Date;

public class Commande {
    private String numCommande;
    private Date dateCommande;
    private Date dateLivraison;
    private int quantiteCommande;
    private String fk_numEmploye;
    private String fk_numFournisseur;
    private int status;

    public Commande(String numCommande, Date dateCommande, Date dateLivraison, int quantiteCommande, String fk_numEmploye, String fk_numFournisseur, int status) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.quantiteCommande = quantiteCommande;
        this.fk_numEmploye = fk_numEmploye;
        this.fk_numFournisseur = fk_numFournisseur;
        this.status = status;
    }

    public String getNumCommande() {
        return numCommande;
    }
    public Date getDateCommande() {
        return dateCommande;
    }
    public Date getDateLivraison() {
        return dateLivraison;
    }
    public int getQuantiteCommande() {
        return quantiteCommande;
    }
    public String getFk_numEmploye() {
        return fk_numEmploye;
    }
    public String getFk_numFournisseur() {
        return fk_numFournisseur;
    }
    public int getStatus() {return status;}

}
