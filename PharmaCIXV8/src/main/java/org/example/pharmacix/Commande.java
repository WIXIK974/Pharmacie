package org.example.pharmacix;

import java.sql.Date;

public class Commande {
    private Integer numCommande;
    private Date dateCommande;
    private Date dateLivraison;
    private int quantiteCommande;
    private Integer fk_idPersonne;
    private String fk_numFournisseur;
    private int status;

    public Commande(Integer numCommande, Date dateCommande, Date dateLivraison, int quantiteCommande, Integer fk_idPersonne, String fk_numFournisseur, int status) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.quantiteCommande = quantiteCommande;
        this.fk_idPersonne = fk_idPersonne;
        this.fk_numFournisseur = fk_numFournisseur;
        this.status = status;
    }

    public Integer getNumCommande() {
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
    public Integer getFk_idPersonne() {
        return fk_idPersonne;
    }
    public String getFk_numFournisseur() {
        return fk_numFournisseur;
    }
    public int getStatus() {return status;}

}
