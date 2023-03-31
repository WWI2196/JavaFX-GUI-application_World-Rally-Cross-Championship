module com.example.cm1601_coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;


    opens com.example.cm1601_coursework to javafx.fxml;
    exports com.example.cm1601_coursework;
}