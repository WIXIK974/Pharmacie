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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VenteController {

    @FXML
    private TableView<Vente> tableVente;

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

    private final VenteDAO venteDAO = new VenteDAO();
    private final ObservableList<Vente> venteList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        assert numVenteColumn != null : "numVenteColumn is not injected. Check fx:id in FXML.";
        numVenteColumn.setCellValueFactory(new PropertyValueFactory<>("numVente"));
        dateVenteColumn.setCellValueFactory(new PropertyValueFactory<>("dateVente"));
        fk_numeroSSColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numeroSS"));
        fk_numEmployeColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numEmploye"));
        fk_numMedicamentColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numMedicament"));
        fk_numOrdonnanceColumn.setCellValueFactory(new PropertyValueFactory<>("fk_numOrdonnance"));

        rafraichirTable();
    }

    public void rafraichirTable() {
        venteList.setAll(venteDAO.getAllVentes());
        tableVente.setItems(venteList);
    }

    @FXML
    private void ajouterVente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/AjSupStock/AjouterVente.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Ajouter une vente"); 

            AjouterVente controller = loader.getController();
            controller.setVenteController(this); // Si tu as ce setter

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menuview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/Menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

