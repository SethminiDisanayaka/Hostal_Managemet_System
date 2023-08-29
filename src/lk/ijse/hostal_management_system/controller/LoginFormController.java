package lk.ijse.hostal_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
        @FXML
        private Button btnLogin;

        @FXML
        private TextField txtPassowrd;

        @FXML
        private TextField txtUsername;

        @FXML
        void LoginButtonOnAction(ActionEvent event) throws IOException {
                Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene dashboardScene = new Scene(dashboardParent);
                stage.setScene(dashboardScene);
                stage.show();
        }

        @FXML
        void ToggleButtonOnAction(ActionEvent event) {

        }

        public void ForgotButtonOnAction(ActionEvent actionEvent) {

        }

        public void SignupButtonOnAction(ActionEvent actionEvent) {

        }
}

