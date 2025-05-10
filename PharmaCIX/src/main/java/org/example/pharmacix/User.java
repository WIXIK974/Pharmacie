package org.example.pharmacix;

public class User {

    private int id;
    private String username;
    private int status; // 1 = employé ayant les droits, 0 = employé sans droits

    public User(int id, String username, int status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Ajoute d'autres getters et setters si nécessaire
}
