package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

public class AjouterCommandeController {

    @FXML
    private DatePicker dateCommandePicker;

    @FXML
    private DatePicker dateLivraisonPicker;

    @FXML
    private TextField quantiteField;

    @FXML
    private TextField fkFournisseurField;

    private CommandeController commandeController;

    public void setCommandeController(CommandeController controller) {
        this.commandeController = controller;
    }

    @FXML
    private void validerAjout() {
        if (getData.status != 1) {
            showAlert(Alert.AlertType.ERROR, "Permission refusée", "Vous n'avez pas la permission d'ajouter une commande.");
            return;
        }

        try {
            LocalDate dateCommandeLD = dateCommandePicker.getValue();
            LocalDate dateLivraisonLD = dateLivraisonPicker.getValue();
            int quantite = Integer.parseInt(quantiteField.getText().trim());
            String fournisseurID = fkFournisseurField.getText().trim();

            if (dateCommandeLD == null || dateLivraisonLD == null || quantite <= 0 || fournisseurID.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champs manquants ou invalides", "Veuillez remplir tous les champs avec des valeurs valides.");
                return;
            }


            Commande commande = new Commande(
                    null, // numCommande auto-incrémenté
                    Date.valueOf(dateCommandeLD),
                    Date.valueOf(dateLivraisonLD),
                    quantite,
                    getData.idPersonne,
                    fournisseurID,
                    getData.status
            );

            CommandeDAO dao = new CommandeDAO();
            boolean success = dao.ajouterCommande(commande);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Commande ajoutée avec succès.");
                if (commandeController != null) {
                    commandeController.rafraichirTable();
                }
                fermerFenetre();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de l'ajout de la commande.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de format", "Veuillez saisir des valeurs valides pour la quantité et l'ID de l'employé.");
        }
    }

    private void fermerFenetre() {
        Stage stage = (Stage) quantiteField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
