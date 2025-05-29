package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {

    @FXML
    private PasswordField motdepasse;

    @FXML
    private TextField utilisateur;

    @FXML
    private void connectionadmin() {
        String sql = "SELECT * FROM Employe WHERE identifiant = ?";

        try (Connection connect = connector.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            if (utilisateur.getText().isEmpty() || motdepasse.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le champ est vide");
                return;
            }

            prepare.setString(1, utilisateur.getText());

            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    String hashedPassword = result.getString("password");

                    // Vérifier le mot de passe avec BCrypt
                    if (BCrypt.checkpw(motdepasse.getText(), hashedPassword)) {
                        getData.username = utilisateur.getText();
                        getData.status = result.getInt("status");
                        getData.idPersonne = result.getInt("idPersonne");

                        showAlert(Alert.AlertType.INFORMATION, "Connexion réussie", "Bienvenue " + getData.username + "!");

                        // Fermer la fenêtre actuelle
                        Stage stageActuel = (Stage) motdepasse.getScene().getWindow();
                        stageActuel.close();

                        // Charger la nouvelle fenêtre
                        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Identifiant ou mot de passe incorrect");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Identifiant ou mot de passe incorrect");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite !");
        }
    }

    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
