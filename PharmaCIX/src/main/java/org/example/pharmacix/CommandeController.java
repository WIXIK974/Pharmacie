package org.example.pharmacix;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommandeController {
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
    private TableView<Commande> CommandeTable;

    @FXML
    private TableColumn<Commande, Integer> numCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateLivraisonColumn ;
    @FXML
    private TableColumn<Commande, Integer> quantiteCommandeColumn ;
    @FXML
    private TableColumn<Commande, String> fk_numFournisseurColumn ;
    @FXML
    private TableColumn<Commande, String> fk_numEmployeColumn ;
    @FXML
    private TableColumn<Commande, Integer> statusColumn;

    /**
     * Méthode appelée automatiquement au chargement de la vue FXML.
     */
    @FXML
    public void initialize() {
        // Associe chaque colonne à un attribut de la classe Employe (via les getters)
        numCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("numCommande"));
        dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        dateLivraisonColumn .setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
        quantiteCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("quantiteCommande"));
        fk_numFournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numFournisseur"));
        fk_numEmployeColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numEmploye"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        // Charge les données depuis la base de données et les affiche dans la table
        ObservableList<Commande> listeCommande = chargerCommandesDepuisBD();
        CommandeTable.setItems(listeCommande); // Ajoute la liste à la TableView

        /**
         * Méthode pour récupérer tous les employés depuis la base de données.
         */


    }
    /**
     * @return Une liste observable d'employés.
     */
    private ObservableList<Commande> chargerCommandesDepuisBD () {
        ObservableList<Commande> commandes = FXCollections.observableArrayList();


        /**
         * Requête SQL pour récupérer tous les employés
         */
        String query = "SELECT numCommande, dateCommande, dateLivraison, quantiteCommande,fk_numEmploye, fk_numFournisseur, status FROM Commande";

        try (
                // Connexion à la base de données via la classe connector
                Connection conn = connector.connectDb();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            // Parcourt le résultat ligne par ligne
            while (rs.next()) {
                // Récupération de chaque champ de la ligne
                int numCommande = rs.getInt("numCommande");
                Date dateCommande = rs.getDate("dateCommande");
                Date dateLivraison = rs.getDate("dateLivraison");
                Integer quantiteCommande = rs.getInt("quantiteCommande");
                String fk_numEmploye = rs.getString("fk_numEmploye");
                String fk_numFournisseur = rs.getString("fk_numFournisseur");
                int status = rs.getInt("status");

                // Création d'un objet Employe avec les données
                Commande commande = new Commande(numCommande, dateCommande, dateLivraison, quantiteCommande,fk_numEmploye,fk_numFournisseur, status);

                // Ajout à la liste
                commandes.add(commande);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Affiche les erreurs dans la console
        }

        return commandes; // Retourne la liste remplie
    }
}
