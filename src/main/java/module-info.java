module org.example.generatechecks {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.generatechecks to javafx.fxml;
    exports org.example.generatechecks;
}