package lk.ijse.hostal_management_system.controller;

import animatefx.animation.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal_management_system.bo.BOFactory;
import lk.ijse.hostal_management_system.bo.custom.UserBO;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordFormController {

    @FXML
    private Button btnchange;

    @FXML
    private Button btnconfirm;

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane pane2;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtpasswordnew;

    @FXML
    private TextField txtusername;

    @FXML
    private TextField txtusernamenew;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.Type.USER);
    @FXML
    void changeButtonOnAction(ActionEvent event) throws IOException {
        String newUserName = txtusernamenew.getText();
        String newPw = txtpasswordnew.getText();

        if (isValidUserName() && isValidPassword()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Confirm Update ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.YES) {

                boolean isUpdated = userBO.updateUser_Pw(newUserName, newPw);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, " Changes Saved !").show();

                    pane.toBack();
                    pane2.toFront();

                    txtusernamenew.clear();
                    txtpasswordnew.clear();

                    Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene dashboardScene = new Scene(dashboardParent);
                    stage.setScene(dashboardScene);
                    stage.show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong Inputs \nTry again !").show();
                }
            } else {
                pane.toFront();
                pane2.toBack();
            }
        }

    }

    @FXML
    void confirmButtonOnAction(ActionEvent event) {

        Shake shakeUserName = new Shake(txtusername);
        Shake shakePassword = new Shake(txtpassword);

        if (isCorrectUserName() && isCorrectPassword()) {
            pane.toFront();
            pane2.toBack();

            txtpassword.clear();
            txtusername.clear();

        } else {
            if (!isCorrectUserName()) {
                shakeUserName.play();
                txtusername.requestFocus();
            }
            if (!isCorrectPassword()) {
                shakePassword.play();
                txtpassword.requestFocus();
            }
        }
    }
    private boolean isCorrectUserName() {
        String user = userBO.getUser("1");
        if (user == null) {
            new Alert(Alert.AlertType.ERROR, " Database Error !").show();
            return false;
        }
        return txtusername.getText().equals(user);
    }
    private boolean isCorrectPassword() {
        String password = userBO.getPassword("1");
        if (password == null) {
            new Alert(Alert.AlertType.ERROR, " Database Error !").show();
            return false;
        }
        return txtpassword.getText().equals(password);
    }
    private boolean isValidUserName() {
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z]{4,}$");
        Matcher userNameMatcher = userNamePattern.matcher(txtusernamenew.getText());

        boolean userNameIsMatches = userNameMatcher.matches();
        if (userNameIsMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtusernamenew);
            txtusernamenew.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidPassword() {
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(txtpasswordnew.getText());

        boolean passwordIsMatches = passwordMatcher.matches();
        if (passwordIsMatches) {
            return true;
        } else {
            Shake shakePW = new Shake(txtpasswordnew);
            txtpasswordnew.requestFocus();
            shakePW.play();
            return false;
        }
    }
}