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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EmployeController {

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

    // TableView (table graphique) et colonnes liées aux propriétés de la classe Employe
    @FXML
    private TableView<Employe> employeTable;

    @FXML
    private TableColumn<Employe, Integer> idPersonneColumn;
    @FXML
    private TableColumn<Employe, String> professionColumn;
    @FXML
    private TableColumn<Employe, Float> salaireColumn;
    @FXML
    private TableColumn<Employe, String> typeContratColumn;
    @FXML
    private TableColumn<Employe, String> statusColumn;

    /**
     * Méthode appelée automatiquement au chargement de la vue FXML.
     */
    @FXML
    public void initialize() {
        // Associe chaque colonne à un attribut de la classe Employe (via les getters)
        idPersonneColumn.setCellValueFactory(new PropertyValueFactory<>("idPersonne"));
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeContratColumn.setCellValueFactory(new PropertyValueFactory<>("typeContrat"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        // Charge les données depuis la base de données et les affiche dans la table
        ObservableList<Employe> listeEmployes = chargerEmployesDepuisBD();
        employeTable.setItems(listeEmployes); // Ajoute la liste à la TableView

        /**
         * Méthode pour récupérer tous les employés depuis la base de données.
         */


        }
    /**
     * @return Une liste observable d'employés.
     */
    private ObservableList<Employe> chargerEmployesDepuisBD () {
    ObservableList<Employe> employes = FXCollections.observableArrayList();


        /**
         * Requête SQL pour récupérer tous les employés
         */
        String query = "SELECT idPersonne, profession, salaire, typecontrat, status FROM Employe";

        try (
                // Connexion à la base de données via la classe connector
                Connection conn = connector.connectDb();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            // Parcourt le résultat ligne par ligne
            while (rs.next()) {
                // Récupération de chaque champ de la ligne
                int idPersonne = rs.getInt("idPersonne");
                String profession = rs.getString("profession");
                float salaire = rs.getFloat("salaire");
                String typeContrat = rs.getString("typeContrat");
                String status = rs.getString("status");

                // Création d'un objet Employe avec les données
                Employe employee = new Employe(idPersonne, profession, salaire, typeContrat, status);

                // Ajout à la liste
                employes.add(employee);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Affiche les erreurs dans la console
        }

        return employes; // Retourne la liste remplie
        }
}
