package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class AjouterVente {

    @FXML private TextField txtNumeroSS;
    @FXML private TextField txtNumEmploye;
    @FXML private TextField txtNumMedicament;
    @FXML private TextField txtNumOrdonnance;

    private final VenteDAO venteDAO = new VenteDAO();
    private VenteController venteController;

    @FXML
    private void validerAjout() {
        String numeroSS = txtNumeroSS.getText().trim();
        String numEmployeStr = txtNumEmploye.getText().trim();
        String numMedicament = txtNumMedicament.getText().trim();
        String numOrdonnanceStr = txtNumOrdonnance.getText().trim();

        if (numeroSS.isEmpty() || numEmployeStr.isEmpty() || numMedicament.isEmpty() || numOrdonnanceStr.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        int numEmploye;
        int numOrdonnance;
        try {
            numEmploye = Integer.parseInt(numEmployeStr);
        } catch (NumberFormatException e) {
            afficherErreur("Numéro d'employé invalide.");
            return;
        }

        try {
            numOrdonnance = Integer.parseInt(numOrdonnanceStr);
        } catch (NumberFormatException e) {
            afficherErreur("Numéro d'ordonnance invalide.");
            return;
        }

        // Utilisation de la date actuelle
        java.sql.Date dateActuelle = new java.sql.Date(System.currentTimeMillis());

        Vente vente = new Vente(0, dateActuelle, numeroSS, numEmploye, numMedicament, numOrdonnance);

        if (venteDAO.ajouterVente(vente)) {
            venteController.rafraichirTable();
            System.out.println("Vente ajoutée avec ID: " + vente.getNumVente()); // optional
            ((Stage) txtNumeroSS.getScene().getWindow()).close();
        } else {
            afficherErreur("Erreur lors de l'ajout de la vente.");
        }
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public void setVenteController(VenteController controller) {
        this.venteController = controller;
    }
}
