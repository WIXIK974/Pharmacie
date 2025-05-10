package org.example.pharmacix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ajouterMedicament {

    // Méthode pour ajouter un médicament à la base de données
    public void ajouterMedicament() {
        // Créer un médicament (à adapter selon ta classe Medicament)
        Medicament medicament = new Medicament("Nom", "Numéro", "Tableau", 10.0f, "500mg", "Type", true);

        // Connexion à la base de données et ajout du médicament
        try (Connection conn = connector.connectDb()) {
            String sql = "INSERT INTO medicaments (nom, numero, tableau, prix, quantiteParUnite, type, en_vente_libre) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, medicament.getNom());
                stmt.setString(2, medicament.getNumMedicament());
                stmt.setString(3, medicament.getTableau());
                stmt.setDouble(4, medicament.getPrix());
                stmt.setString(5, medicament.getQuantiteParUnite());
                stmt.setString(6, medicament.getType());
                stmt.setBoolean(7, medicament.isEnVenteLibre());

                // Exécuter la requête d'insertion
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Médicament ajouté avec succès !");
                } else {
                    System.out.println("Aucun médicament ajouté.");
                }
            }
        } catch (SQLException e) {
            // Afficher l'erreur en cas d'échec de la connexion ou de la requête
            e.printStackTrace();
        }
    }

    // Méthode main pour tester l'ajout de médicament
    public static void main(String[] args) {
        ajouterMedicament ajouter = new ajouterMedicament();
        ajouter.ajouterMedicament();
    }
}
