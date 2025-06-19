module org.example.generatechecks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.generatechecks to javafx.fxml;
    exports org.example.generatechecks;
}