package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterEmployeController {

    @FXML private TextField txtProfession;
    @FXML private TextField txtSalaire;
    @FXML private TextField txtTypeContrat;
    @FXML private TextField txtStatus;
    @FXML private TextField txtIdentifiant;
    @FXML private TextField txtPassword;

    private EmployeController employeController;

    @FXML
    private void validerAjout() {
        String profession = txtProfession.getText().trim();
        Float salaire = Float.valueOf(txtSalaire.getText().trim());
        String typeContrat = txtTypeContrat.getText().trim();
        int status = Integer.parseInt(txtStatus.getText().trim());
        String identifiant = txtIdentifiant.getText().trim();
        String password = txtPassword.getText().trim();

        if (profession.isEmpty() || salaire <= 0 || typeContrat.isEmpty() || (status != 1 && status != 0) || identifiant.isEmpty() || password.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        try {
            // Créez un objet Employe avec les détails saisis
            Employe employe = new Employe(0, profession, salaire, typeContrat, status); // idPersonne est 0 car il sera auto-incrémenté

            // Ajoutez ici la logique pour sauvegarder l'employé dans la base de données
            EmployeDAO employeDAO = new EmployeDAO();
            if (employeDAO.ajouterEmploye(employe, identifiant, password)) {
                if (employeController != null) {
                    employeController.rafraichirTable();
                }
                ((Stage) txtProfession.getScene().getWindow()).close();
            } else {
                afficherErreur("Erreur lors de l'ajout de l'employé.");
            }
        } catch (NumberFormatException e) {
            afficherErreur("Veuillez entrer des valeurs valides pour le salaire et le status.");
        }
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public void setEmployeController(EmployeController controller) {
        this.employeController = controller;
    }
}
