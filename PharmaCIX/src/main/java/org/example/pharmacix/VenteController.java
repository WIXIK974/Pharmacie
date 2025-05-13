package org.example.pharmacix;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VenteController {

    //Pour pouvoir retourner au menu
    @FXML
    public void menuview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        // Récupérer la fenêtre actuelle et changer la scène
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private TableView<Vente> venteTable;

    @FXML
    private TableColumn<Vente, Integer> numVenteColumn;
    @FXML
    private TableColumn<Vente, Date> dateVenteColumn;
    @FXML
    private TableColumn<Vente, String> fk_numeroSSColumn;
    @FXML
    private TableColumn<Vente, String> fk_numEmployeColumn;
    @FXML
    private TableColumn<Vente, String> fk_numMedicamentColumn;
    @FXML
    private TableColumn<Vente, Integer> fk_numOrdonnanceColumn;

    /**
     * Méthode appelée automatiquement au chargement de la vue FXML.
     */
    @FXML
    public void initialize() {
        System.out.println("Chargement des ventes...");
        System.out.println("VenteTable est null ? " + (venteTable == null));


        // Vérifie que le TableView et ses colonnes sont bien liés
        System.out.println("Nombre de colonnes : " + venteTable.getColumns().size());

        // Liaison des colonnes avec les propriétés de la classe Vente
        numVenteColumn.setCellValueFactory(new PropertyValueFactory<>("numVente"));
        dateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("dateVente"));
        fk_numeroSSColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numeroSS"));
        fk_numEmployeColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numEmploye"));
        fk_numMedicamentColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numMedicament"));
        fk_numOrdonnanceColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numOrdonnance"));

        // Chargement des données depuis la base
        ObservableList<Vente> listeVentes = chargerVentesDepuisBD();

        // Affiche le nombre de lignes récupérées
        System.out.println("Nombre de ventes chargées : " + listeVentes.size());

        // Affectation à la table
        venteTable.setItems(listeVentes);
    }
    /**
     * @return Une liste observable d'employés.
     */
    private ObservableList<Vente> chargerVentesDepuisBD () {
        ObservableList<Vente> vente = FXCollections.observableArrayList();



        /**
         * Requête SQL pour récupérer toutes les
         */
        String query = "SELECT numVente, dateVente, fk_numeroSS, fk_numEmploye, fk_numMedicament, fk_numOrdonnance FROM Vente";

        try (
                // Connexion à la base de données via la classe connector
                Connection conn = connector.connectDb();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            // Parcourt le résultat ligne par ligne
            while (rs.next()) {
                // Récupération de chaque champ de la ligne
                int numVente = rs.getInt("numVente");
                Date dateVente = rs.getDate("dateVente");
                String fk_numeroSS = rs.getString("fk_numeroSS");
                String fk_numEmploye = rs.getString("fk_numEmploye");
                String fk_numMedicament = rs.getString("fk_numMedicament");
                int fk_numOrdonnance = rs.getInt("fk_numOrdonnance");

                // Création d'un objet Employe avec les données
                Vente ventes = new Vente(numVente, dateVente, fk_numeroSS, fk_numEmploye, fk_numMedicament, fk_numOrdonnance);

                // Ajout à la liste
                vente.add(ventes);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Affiche les erreurs dans la console
        }

        return vente; // Retourne la liste remplie
    }
}

