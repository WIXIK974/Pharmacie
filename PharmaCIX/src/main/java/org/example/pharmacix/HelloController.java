package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {

    @FXML
    private PasswordField motdepasse;

    @FXML
    private Button seconnecter;

    @FXML
    private TextField utilisateur;

    private double x = 0;
    private double y = 0;

    public void connectionadmin() {
        String sql = "SELECT * FROM Employe WHERE identifiant = ? AND password = ?";

        try (Connection connect = connector.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            if (utilisateur.getText().isEmpty() || motdepasse.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le champ est vide");
                return;
            }

            prepare.setString(1, utilisateur.getText());
            prepare.setString(2, motdepasse.getText());

            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    System.out.println("Test1");
                    getData.username = utilisateur.getText();

                    showAlert(Alert.AlertType.INFORMATION, "Connexion réussie", "Bienvenue " + getData.username + "!"); // reprend le prenom de l'utilisateur puis crée une boite de dialogue avec

                    // Fermer la fenêtre actuelle
                    Stage stageActuel = (Stage) seconnecter.getScene().getWindow();
                    stageActuel.close();

                    // Charger la nouvelle fenêtre
                    Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setResizable(false);


                    // Permettre le déplacement de la fenêtre
                    root.setOnMousePressed(event -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged(event -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    stage.setScene(scene);
                    stage.show();

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

    @FXML
    public void initialize() {
        motdepasse.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                connectionadmin();
            }
        });

        utilisateur.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                connectionadmin();
            }
        });
    }




}
