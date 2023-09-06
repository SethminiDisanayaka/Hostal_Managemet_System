package lk.ijse.hostal_management_system.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.hostal_management_system.bo.BOFactory;
import lk.ijse.hostal_management_system.bo.custom.UserBO;

import java.io.IOException;

public class LoginFormController {
        @FXML
        public Label shownPassword;
        @FXML
        public ToggleButton btntgl;
        @FXML
        public ImageView imgPasswordView;
        @FXML
        private Button btnLogin;

        @FXML
        private TextField txtPassowrd;

        @FXML
        private TextField txtUsername;

        UserBO userBO =(UserBO) BOFactory.getBoFactory().getBO(BOFactory.Type.USER);
        @FXML
        void LoginButtonOnAction(ActionEvent event) throws IOException {
//                Shake shakeUserName = new Shake(txtUsername);
//                Shake shakePassword = new Shake(txtPassowrd);
//
//                if( isCorrectPassword() && isCorrectUserName()){
//                        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
//                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                        Scene dashboardScene = new Scene(dashboardParent);
//                        stage.setScene(dashboardScene);
//                        stage.show();
//
//                }else if (isCorrectPassword() && !isCorrectUserName()) {
//                        txtUsername.requestFocus();
//                        shakeUserName.play();
//
//                } else if (!isCorrectPassword() && isCorrectUserName()) {
//                        txtPassowrd.requestFocus();
//                        shakePassword.play();
//
//                } else{
//                        new Alert(Alert.AlertType.ERROR,"Try again !").show();
//                        txtPassowrd.clear();
//                        txtUsername.clear();
//                }
                Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene dashboardScene = new Scene(dashboardParent);
                stage.setScene(dashboardScene);
                stage.show();
        }
        private boolean isCorrectUserName() {
                String user = userBO.getUser("1");
                if(user == null){
                        new Alert(Alert.AlertType.ERROR," Database Error !").show();
                        return false;
                }
                return txtUsername.getText().equals(user);
        }

        private boolean isCorrectPassword() {
                String password = userBO.getPassword("1");
                if(password == null){
                        new Alert(Alert.AlertType.ERROR," Database Error !").show();
                        return false;
                }
                return txtPassowrd.getText().equals(password);
        }
        @FXML
        void ToggleButtonOnAction(ActionEvent event) {
                if (btntgl.isSelected()) {
                        shownPassword.setVisible(true);
                        shownPassword.textProperty().bind(Bindings.concat(txtPassowrd.getText()));
                        btntgl.setText("Hide");
                        imgPasswordView.setImage(new Image("resources/img/eye-close.png"));

                }else{
                        shownPassword.setVisible(false);
                        txtPassowrd.setVisible(true);
                        btntgl.setText("Show");
                        imgPasswordView.setImage(new Image("resources/img/eye-open.png"));
                }
        }

        public void ForgotButtonOnAction(ActionEvent actionEvent) {
                new Alert(Alert.AlertType.INFORMATION,"Please contact Developer !\n0740113917").show();
        }

        public void SignupButtonOnAction(ActionEvent actionEvent) {

        }
}

