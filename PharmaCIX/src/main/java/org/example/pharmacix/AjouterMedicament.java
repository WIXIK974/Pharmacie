
package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AjouterMedicament {

    @FXML private TextField txtNom;
    @FXML private TextField txtDescription;
    @FXML private TextField txtPrix;
    @FXML private TextField txtType;
    @FXML private TextField txtTableau;
    @FXML private CheckBox checkEnVenteLibre;
    @FXML private TextField txtQuantite;

    private final MedicamentDAO medicamentDAO = new MedicamentDAO();
    private MedicamentsController medicamentsController;

    @FXML
    private void validerAjout() {
        String nom = txtNom.getText().trim();
        String description = txtDescription.getText().trim();
        String type = txtType.getText().trim();
        String tableau = txtTableau.getText().trim();
        boolean enVenteLibre = checkEnVenteLibre.isSelected();

        if (nom.isEmpty() || description.isEmpty() || txtPrix.getText().isEmpty() || type.isEmpty()
                || tableau.isEmpty() || txtQuantite.getText().isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        double prix;
        String quantite;
        try {
            prix = Double.parseDouble(txtPrix.getText());
            if (prix < 0) {
                afficherErreur("Le prix ne peut pas être négatif.");
                return;
            }
        } catch (NumberFormatException e) {
            afficherErreur("Veuillez entrer un prix valide.");
            return;
        }

        quantite = txtQuantite.getText();
        if (quantite.isEmpty()) {
            afficherErreur("Veuillez entrer une quantité.");
            return;
        }

        Medicament medicament = new Medicament("0", nom , prix, type, tableau, enVenteLibre, quantite);

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



