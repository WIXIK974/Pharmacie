package org.example.pharmacix;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicamentsController {

    @FXML private TableView<Medicament> tableMedicaments;
    @FXML private TableColumn<Medicament, String> colId; // numMedicament est un String
    @FXML private TableColumn<Medicament, String> colNom;
    @FXML private TableColumn<Medicament, String> colDescription;
    @FXML private TableColumn<Medicament, Double> colPrix;
    @FXML private TableColumn<Medicament, String> colType;
    @FXML private TableColumn<Medicament, String> colTableau;
    @FXML private TableColumn<Medicament, Boolean> colEnVenteLibre;
    @FXML private TableColumn<Medicament, Integer> colQuantite; // Quantité reste un Integer

    private final MedicamentDAO medicamentDAO = new MedicamentDAO();
    private final ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("numMedicament"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTableau.setCellValueFactory(new PropertyValueFactory<>("tableau"));
        colEnVenteLibre.setCellValueFactory(new PropertyValueFactory<>("enVenteLibre"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        rafraichirTable();
    }

    @FXML
    private void ajouterMedicament() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/AjSupStock/AjouterMedicament.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Ajouter un médicament");

            AjouterMedicament controller = loader.getController();
            controller.setMedicamentsController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void supprimerMedicament() {
        Medicament medicament = tableMedicaments.getSelectionModel().getSelectedItem();
        if (medicament != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer ce médicament ?", ButtonType.YES, ButtonType.NO);
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES && medicamentDAO.supprimerMedicament(medicament.getNumMedicament())) {
                    medicamentList.remove(medicament);
                }
            });
        }
    }

    @FXML
    private void ajouterStock() {
        Medicament medicament = tableMedicaments.getSelectionModel().getSelectedItem();
        if (medicament != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ajouter au stock");
            dialog.setHeaderText("Quantité à ajouter au stock :");
            dialog.setContentText("Quantité :");


        }
    }


    public void rafraichirTable() {
        medicamentList.setAll(medicamentDAO.getAllMedicaments());
        tableMedicaments.setItems(medicamentList);
    }

    public void menuview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void validerSuppressionMedicament(ActionEvent actionEvent) {
        // Empty method, can be filled if needed
    }
}
