package lk.ijse.hostal_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent =  FXMLLoader.load(getClass().getResource("/resources/view/Login_Form.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("D24 Hostel Management System");
        stage.centerOnScreen();

        stage.show();

    }
}
