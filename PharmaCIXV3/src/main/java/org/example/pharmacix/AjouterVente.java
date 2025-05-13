package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterVente {
    @FXML private TextField txtNumeroSS;
    @FXML private TextField txtNumMedicament;
    @FXML private TextField txtNumOrdonance;
    @FXML private CheckBox checkEnVenteLibre;
    @FXML private TextField txtQuantite; /* à revoir le système de vente */

    private final MedicamentDAO medicamentDAO = new MedicamentDAO();
    private MedicamentsController medicamentsController;

    @FXML
    private void validerAjout() {
        String numeroSS = txtNumeroSS.getText().trim();
        String numOrdonance = txtNumOrdonance.getText().trim();
        String numMedicament = txtNumMedicament.getText().trim();
        boolean enVenteLibre = checkEnVenteLibre.isSelected();
        String quantite= txtQuantite.getText().trim();

        if (numeroSS.isEmpty() || numOrdonance.isEmpty() || numMedicament.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        quantite = txtQuantite.getText();
        if (quantite.isEmpty()) {
            afficherErreur("Veuillez entrer une quantité.");
            return;
        }

        Vente vente = new Medicament("0", numeroSS , numOrdonance, numMedicament, enVenteLibre, enVenteLibre, quantite);
        /*---------------------------------------------------------------------------------------------------------------------*/

        if (medicamentDAO.ajouterMedicament(medicament)) {
            medicamentsController.rafraichirTable();
            ((Stage) txtNom.getScene().getWindow()).close();
        } else {
            afficherErreur("Erreur lors de l'ajout du médicament.");
        }
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public void setMedicamentsController(MedicamentsController controller) {
        this.medicamentsController = controller;
    }
}
