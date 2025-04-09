module org.example.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.biblioteca to javafx.fxml;
    exports org.example.biblioteca;
}