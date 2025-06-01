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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeController {

    @FXML
    public void menuview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void ajouterEmploye() {
        if (getData.status != 1) {
            showAlert(Alert.AlertType.ERROR, "Permission refusée", "Vous n'avez pas la permission d'ajouter un employé.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pharmacix/AjSupStock/AjouterEmploye.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Ajouter un Employé");

            AjouterEmployeController controller = loader.getController();
            controller.setEmployeController(this);

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
    private TableColumn<Employe, Integer> statusColumn;

    @FXML
    public void initialize() {
        idPersonneColumn.setCellValueFactory(new PropertyValueFactory<>("idPersonne"));
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeContratColumn.setCellValueFactory(new PropertyValueFactory<>("typeContrat"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<Employe> listeEmployes = chargerEmployesDepuisBD();
        employeTable.setItems(listeEmployes);
    }

    private ObservableList<Employe> chargerEmployesDepuisBD() {
        ObservableList<Employe> employes = FXCollections.observableArrayList();

        String query = "SELECT idPersonne, profession, salaire, typeContrat, status FROM Employe";

        try (
                Connection conn = connector.connectDb();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                int idPersonne = rs.getInt("idPersonne");
                String profession = rs.getString("profession");
                int salaire = rs.getInt("salaire");
                String typeContrat = rs.getString("typeContrat");
                int status = rs.getInt("status");

                Employe employee = new Employe(idPersonne, profession, salaire, typeContrat, status);
                employes.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employes;
    }

    public void rafraichirTable() {
        ObservableList<Employe> listeEmployes = chargerEmployesDepuisBD();
        employeTable.setItems(listeEmployes);
    }
}
