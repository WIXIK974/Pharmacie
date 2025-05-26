package org.example.pharmacix;

public class User {

    private int id;
    private String username;  // Identifiant de l'utilisateur
    private int status;  // 1 = employé avec droits, 0 = employé sans droits

    // Constructeur
    public User(int id, String username, int status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    // Getter pour le statut
    public int getStatus() {
        return status;
    }

    // Setter pour le statut
    public void setStatus(int status) {
        this.status = status;
    }

    // Getter pour le nom d'utilisateur
    public String getUsername() {
        return username;
    }

    // Setter pour le nom d'utilisateur
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter pour l'id
    public int getId() {
        return id;
    }

    // Setter pour l'id
    public void setId(int id) {
        this.id = id;
    }
}
