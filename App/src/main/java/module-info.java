module com.mycompany.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.app to javafx.fxml;
    exports com.mycompany.app;
}
