package lk.ijse.hostal_management_system.controller;

import animatefx.animation.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.hostal_management_system.bo.BOFactory;
import lk.ijse.hostal_management_system.bo.custom.StudentBO;
import lk.ijse.hostal_management_system.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentFormController {

    @FXML
    public DatePicker datePicker;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSave;

    @FXML
    private RadioButton radFemale;

    @FXML
    private RadioButton radMale;

    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblContact;

    @FXML
    private TableColumn<?, ?> tblDob;

    @FXML
    private TableColumn<?, ?> tblGender;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtStudentAddress;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtSyudentId;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.Type.STUDENT);

    @FXML
    void BackButtonOnAction(ActionEvent event) throws IOException {

        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene dashboardScene = new Scene(dashboardParent);
        stage.setScene(dashboardScene);
        stage.show();
    }

    @FXML
    void CancelButtonOnAvtion(ActionEvent event) {
        clearFields();
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String idText = txtSyudentId.getText();

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(idText);

            Boolean isAdded = studentBO.deleteStudent(studentDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, " Student Deleted ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }
    }

    @FXML
    void EditButtonOnAction(ActionEvent event) {

    }

    @FXML
    void NewButtonOnAction(ActionEvent event) {

    }

    @FXML
    void NextButtonOnAction(ActionEvent event) {

    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {

        if(!txtSyudentId.getText().equals("")|| txtStudentName.getText().equals("")||txtContact.getText().equals("")){
            String nameText = txtStudentName.getText();
            String addressText = txtStudentAddress.getText();
            String contactText = txtContact.getText();
            String idText = txtSyudentId.getText();
            String dobText = datePicker.getValue().toString();
            RadioButton rb = (RadioButton) (radMale.isSelected() ? radMale : radFemale);
            String genderText = rb.getText();
        }

    }

    private void clearFields(){
        txtSyudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtContact.clear();
        datePicker.setValue(LocalDate.parse("2000-01-01"));
        radMale.setSelected(true);
    }

    private boolean isValidName() {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}$");
        Matcher matcher = pattern.matcher(txtStudentName.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtStudentName);
            txtStudentName.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

}
