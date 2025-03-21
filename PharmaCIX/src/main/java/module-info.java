module org.example.pharmacix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.pharmacix to javafx.fxml;
    exports org.example.pharmacix;
}