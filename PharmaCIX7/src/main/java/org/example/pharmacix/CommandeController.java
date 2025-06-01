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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void ajouterCommande() {
        if (getData.status != 1) {
            showAlert(Alert.AlertType.ERROR, "Permission refusée", "Vous n'avez pas la permission d'ajouter une commande.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/AjSupStock/AjouterCommande.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Ajouter une Commande");

            AjouterCommandeController controller = loader.getController();
            controller.setCommandeController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private TableView<Commande> commandeTable;

    @FXML
    private TableColumn<Commande, Integer> numCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateLivraisonColumn;
    @FXML
    private TableColumn<Commande, Integer> quantiteCommandeColumn;
    @FXML
    private TableColumn<Commande, String> fk_numFournisseurColumn;
    @FXML
    private TableColumn<Commande, Integer> fk_idPersonneColumn;
    @FXML
    private TableColumn<Commande, Integer> statusColumn;

    @FXML
    public void initialize() {
        numCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("numCommande"));
        dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
        quantiteCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("quantiteCommande"));
        fk_numFournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numFournisseur"));
        fk_idPersonneColumn.setCellValueFactory(new PropertyValueFactory<>("fk_idPersonne"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<Commande> listeCommandes = chargerCommandesDepuisBD();
        commandeTable.setItems(listeCommandes);
    }

    private ObservableList<Commande> chargerCommandesDepuisBD() {
        ObservableList<Commande> commandes = FXCollections.observableArrayList();

        String query = "SELECT numCommande, dateCommande, dateLivraison, quantiteCommande, fk_idPersonne, fk_numFournisseur, status FROM Commande";

        try (
                Connection conn = connector.connectDb();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Integer numCommande = Integer.valueOf(rs.getString("numCommande"));
                Date dateCommande = rs.getDate("dateCommande");
                Date dateLivraison = rs.getDate("dateLivraison");
                Integer quantiteCommande = rs.getInt("quantiteCommande");
                Integer fk_idPersonne = rs.getInt("fk_idPersonne");
                String fk_numFournisseur = rs.getString("fk_numFournisseur");
                int status = rs.getInt("status");

                Commande commande = new Commande(numCommande, dateCommande, dateLivraison, quantiteCommande, fk_idPersonne, fk_numFournisseur, status);
                commandes.add(commande);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandes;
    }

    public void rafraichirTable() {
        ObservableList<Commande> listeCommandes = chargerCommandesDepuisBD();
        commandeTable.setItems(listeCommandes);
    }
}