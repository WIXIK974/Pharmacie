package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private TextField fkEmployeField;
    @FXML
    private TextField fkFournisseurField;
    @FXML
    private ComboBox<Integer> statusCombo;

    private CommandeController commandeController;

    public void setCommandeController(CommandeController controller) {
        this.commandeController = controller;
    }

    @FXML
    private void initialize() {
        statusCombo.getItems().addAll(0, 1); // 0 = annulée, 1 = confirmée/en cours
    }

    @FXML
    private void enregistrerCommande() {
        try {
            LocalDate dateCommandeLD = dateCommandePicker.getValue();
            LocalDate dateLivraisonLD = dateLivraisonPicker.getValue();
            int quantite = Integer.parseInt(quantiteField.getText());
            String employeID = fkEmployeField.getText();
            String fournisseurID = fkFournisseurField.getText();
            Integer status = statusCombo.getValue();

            if (dateCommandeLD == null || dateLivraisonLD == null || employeID.isEmpty() || fournisseurID.isEmpty() || status == null) {
                showAlert(Alert.AlertType.WARNING, "Champs manquants", "Veuillez remplir tous les champs.");
                return;
            }

            Commande commande = new Commande(
                    null, // numCommande est auto-incrémenté
                    Date.valueOf(dateCommandeLD),
                    Date.valueOf(dateLivraisonLD),
                    quantite,
                    employeID,
                    fournisseurID,
                    status
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
            showAlert(Alert.AlertType.ERROR, "Erreur de format", "Veuillez saisir une quantité valide.");
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
