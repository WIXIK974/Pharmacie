package org.example.pharmacix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MenuView {

    @FXML
    public void medicamentsview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/BoutonAccueil/MedicamentsMenu.fxml"));
        if (loader.getLocation() == null) {
            System.err.println("File not found: /org/example/pharmacix/BoutonAccueil/MedicamentsMenu.fxml");
            return;
        }
        Parent root = loader.load();

        // Récupérer la fenêtre actuelle et changer la scène
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void employeview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/BoutonAccueil/EmployeMenu.fxml"));
        if (loader.getLocation() == null) {
            System.err.println("File not found: /org/example/pharmacix/EmployeMenu.fxml");
            return;
        }
        Parent root = loader.load();

        // Récupérer la fenêtre actuelle et changer la scène
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void venteview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/BoutonAccueil/VenteMenu.fxml"));
        if (loader.getLocation() == null) {
            System.err.println("File not found: /org/example/pharmacix/BoutonAccueil/VenteMenu.fxml");
            return;
        }
        Parent root = loader.load();

        // Récupérer la fenêtre actuelle et changer la scène
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void deconnexion(ActionEvent event) throws IOException {
        // Fermer la fenêtre actuelle
        Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActuel.close();

        // Charger la fenêtre de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/AccueilView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Connexion");
        stage.show();
    }
}
