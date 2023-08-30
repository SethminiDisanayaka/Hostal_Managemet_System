package lk.ijse.hostal_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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

    }

    private void clearFields(){
        txtSyudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtContact.clear();
        datePicker.setValue(LocalDate.parse("2000-01-01"));
        radMale.setSelected(true);
    }

}
