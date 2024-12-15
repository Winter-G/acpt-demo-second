module com.example.second {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.second to javafx.fxml;
    exports com.example.second;
    exports com.example.second.controller;
    opens com.example.second.controller to javafx.fxml;
    exports com.example.second.tm;
    opens com.example.second.tm to javafx.fxml;
}