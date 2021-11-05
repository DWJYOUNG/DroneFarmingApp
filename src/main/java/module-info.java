module com.smartfarm.smartfarmapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    exports com.smartFarmApp.view;
    opens com.smartFarmApp.view to javafx.fxml;
    exports com.smartFarmApp.dashboard;
    opens com.smartFarmApp.dashboard to javafx.fxml;

}