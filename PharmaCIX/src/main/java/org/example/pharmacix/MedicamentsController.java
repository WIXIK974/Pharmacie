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

/**
 * Contrôleur du tableau des médicaments, chargé d'afficher les données dans l'interface JavaFX.
 */

public class MedicamentsController {
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


    // TableView (table graphique) et colonnes liées aux propriétés de la classe Medicament
    @FXML
    private TableView<Medicament> medicamentTable;

    @FXML
    private TableColumn<Medicament, String> nomColum;
    @FXML
    private TableColumn<Medicament, String> numMedicamentColum;
    @FXML
    private TableColumn<Medicament, String> tableauColum;
    @FXML
    private TableColumn<Medicament, Float> prixColum;
    @FXML
    private TableColumn<Medicament, String> quantiteParUniteColum;
    @FXML
    private TableColumn<Medicament, String> typeColum;
    @FXML
    private TableColumn<Medicament, Boolean> enVenteLibreColum;

    @FXML
    private Button btnAjouter; // Bouton "Ajouter" pour l'ajout de médicaments

    private User currentUser; // Utilisateur actuel (qui effectue l'opération)

    /**
     * Méthode appelée automatiquement au chargement de la vue FXML.
     */
    @FXML
    public void initialize() {
        // Associe chaque colonne à un attribut de la classe Medicament (via les getters)
        nomColum.setCellValueFactory(new PropertyValueFactory<>("nom"));
        numMedicamentColum.setCellValueFactory(new PropertyValueFactory<>("numMedicament"));
        tableauColum.setCellValueFactory(new PropertyValueFactory<>("tableau"));
        prixColum.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteParUniteColum.setCellValueFactory(new PropertyValueFactory<>("quantiteParUnite"));
        typeColum.setCellValueFactory(new PropertyValueFactory<>("type"));
        enVenteLibreColum.setCellValueFactory(new PropertyValueFactory<>("enVenteLibre"));

        /**
         *  Affiche "Oui" ou "Non" dans la colonne au lieu de "true" ou "false"
         */
        enVenteLibreColum.setCellFactory(column -> new TableCell<Medicament, Boolean>() {
            @Override
            protected void updateItem(Boolean value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null); // Pas de texte si cellule vide
                } else {
                    setText(value ? "Oui" : "Non"); // Affiche Oui ou Non
                }
            }
        });

        // Charge les données depuis la base de données et les affiche dans la table
        ObservableList<Medicament> listeMedicaments = chargerMedicamentsDepuisBD();
        medicamentTable.setItems(listeMedicaments); // Ajoute la liste à la TableView

        // Vérifie si l'utilisateur peut ajouter un médicament
        if (!canAddMedicaments()) {
            btnAjouter.setDisable(true); // Désactive le bouton si l'utilisateur n'a pas les droits
        }
    }

    /**
     * Méthode pour récupérer tous les médicaments depuis la base de données.
     * @return Une liste observable de médicaments.
     */
    private ObservableList<Medicament> chargerMedicamentsDepuisBD() {
        ObservableList<Medicament> medicaments = FXCollections.observableArrayList();

        // Requête SQL pour récupérer tous les médicaments
        String query = "SELECT * FROM Medicament";

        try (
                // Connexion à la base de données via la classe connector
                Connection conn = connector.connectDb();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            // Parcourt le résultat ligne par ligne
            while (rs.next()) {
                // Récupération de chaque champ de la ligne
                String nom = rs.getString("nom");
                String numMedicament = rs.getString("numMedicament");
                String tableau = rs.getString("tableau");
                float prix = rs.getFloat("prix");
                String quantiteParUnite = rs.getString("quantiteParUnite");
                String type = rs.getString("type");
                boolean enVenteLibre = rs.getBoolean("enVenteLibre");

                // Création d'un objet Medicament avec les données
                Medicament medicament = new Medicament(nom, numMedicament, tableau, prix, quantiteParUnite, type, enVenteLibre);

                // Ajout à la liste
                medicaments.add(medicament);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Affiche les erreurs dans la console
        }

        return medicaments; // Retourne la liste remplie
    }

    /**
     * Vérifie si l'utilisateur a les droits d'ajouter un médicament (status 1).
     * @return true si l'utilisateur a les droits, false sinon.
     */
    private boolean canAddMedicaments() {
        User currentUser = getCurrentUser();  // Récupère l'utilisateur actuel
        return currentUser != null && currentUser.getStatus() == 1; // Vérifie que l'utilisateur a status = 1
    }


    /**
     * Méthode appelée lors du clic sur le bouton "Ajouter" pour ajouter un médicament.
     */
    @FXML
    private void handleAjouter() {
        if (canAddMedicaments()) {
            showAlert("acces autoriser", "vous etes un vip");
            // Ajouter un médicament à la base de données
            ajouterMedicament();
        } else {
            // Affiche un message d'erreur si l'utilisateur n'a pas les droits
            showAlert("Accès interdit", "Vous n'avez pas les droits nécessaires pour ajouter un médicament.");
        }
    }

    /**
     * Ajoute un médicament à la base de données.
     */
    private void ajouterMedicament() {
        // Exemple de création d'un médicament (les valeurs peuvent venir d'un formulaire)
        Medicament medicament = new Medicament("Nouveau Médicament", "12345", "Tableau A", 25.0f, "1", "Type A", true);

        // Connexion à la base de données pour l'ajout
        try (Connection conn = connector.connectDb()) {
            String sql = "INSERT INTO Medicament (nom, numMedicament, tableau, prix, quantiteParUnite, type, enVenteLibre) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, medicament.getNom());
                stmt.setString(2, medicament.getNumMedicament());
                stmt.setString(3, medicament.getTableau());
                stmt.setFloat(4, medicament.getPrix());
                stmt.setString(5, medicament.getQuantiteParUnite());
                stmt.setString(6, medicament.getType());
                stmt.setBoolean(7, medicament.isEnVenteLibre());

                stmt.executeUpdate(); // Exécute la requête pour insérer le médicament
            }
        } catch (Exception e) {
            e.printStackTrace(); // Affiche les erreurs dans la console
        }
    }

    /**
     * Affiche une alerte avec un message d'erreur.
     * @param title Le titre de l'alerte.
     * @param message Le message d'erreur.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Exemple de méthode pour récupérer l'utilisateur actuel
    private User getCurrentUser() {
        // Cette méthode devrait récupérer l'utilisateur actuellement connecté.
        // Pour l'exemple, on retourne un utilisateur avec status 1 (droit d'ajouter)
        return new User(1, "admin", 1); // Exemple d'utilisateur avec status 1
    }
}
