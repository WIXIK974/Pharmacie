package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

public class AjouterVente {

    @FXML private TextField txtNumeroSS;
    @FXML private TextField txtIdPersonne; // Utilisez txtIdPersonne au lieu de txtNumEmploye
    @FXML private TextField txtNumMedicament;
    @FXML private TextField txtNumOrdonnance;
    @FXML private TextField txtQuantiteVendue;

    private final VenteDAO venteDAO = new VenteDAO();
    private VenteController venteController;

    @FXML
    private void validerAjout() {
        String numeroSS = txtNumeroSS.getText().trim();
        String idPersonneStr = txtIdPersonne.getText().trim(); // Utilisez txtIdPersonne au lieu de txtNumEmploye
        String numMedicament = txtNumMedicament.getText().trim();
        String numOrdonnanceStr = txtNumOrdonnance.getText().trim();
        String quantiteVendue = txtQuantiteVendue.getText().trim();

        if (numeroSS.isEmpty() || idPersonneStr.isEmpty() || numMedicament.isEmpty() || numOrdonnanceStr.isEmpty() || quantiteVendue.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        int idPersonne; // Utilisez idPersonne au lieu de numEmploye
        int numOrdonnance;
        try {
            idPersonne = Integer.parseInt(idPersonneStr); // Utilisez idPersonne au lieu de numEmploye
        } catch (NumberFormatException e) {
            afficherErreur("ID de personne invalide.");
            return;
        }

        try {
            numOrdonnance = Integer.parseInt(numOrdonnanceStr);
        } catch (NumberFormatException e) {
            afficherErreur("Numéro d'ordonnance invalide.");
            return;
        }

        // Vérifiez les valeurs des clés étrangères
        if (!DataValidator.validatePatient(numeroSS)) {
            afficherErreur("Numéro de sécurité sociale invalide.");
            return;
        }

        if (!DataValidator.validateEmploye(idPersonne)) { // Utilisez idPersonne au lieu de numEmploye
            afficherErreur("ID de personne invalide.");
            return;
        }

        if (!DataValidator.validateMedicament(numMedicament)) {
            afficherErreur("Numéro de médicament invalide.");
            return;
        }

        if (!DataValidator.validateOrdonnance(numOrdonnance)) {
            afficherErreur("Numéro d'ordonnance invalide.");
            return;
        }

        // Utilisation de la date actuelle
        Date dateActuelle = new Date();

        Vente vente = new Vente(0, dateActuelle, numeroSS, String.valueOf(idPersonne), numMedicament, numOrdonnance, quantiteVendue); // Utilisez idPersonne au lieu de numEmploye

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
