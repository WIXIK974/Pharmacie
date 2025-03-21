package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private PreparedStatement prepare;
    private Connection connect;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    public void connectionadmin() {
        // Initialisation de la connexion
        connect = connector.connectDb(); // Connexion via votre classe connector

        String sql = "SELECT * FROM Employe WHERE identifiant = ? AND password = ?";

        try {
            // Vérification des champs vides
            if (utilisateur.getText().isEmpty() || motdepasse.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le champ est vide");
                alert.showAndWait();
                return;
            }

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, utilisateur.getText());
            prepare.setString(2, motdepasse.getText());

            result = prepare.executeQuery();

            if (result.next()) {
                // Connexion réussie
                getData.username = utilisateur.getText(); // Remplacez par votre logique si nécessaire

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("connection réussie !");
                alert.showAndWait();

                // Masquer la fenêtre actuelle
                seconnecter.getScene().getWindow().hide();

                // Charger la nouvelle fenêtre
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                // Gestion du déplacement de la fenêtre
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            } else {
                // Identifiant ou mot de passe incorrect
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources pour éviter les fuites
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
