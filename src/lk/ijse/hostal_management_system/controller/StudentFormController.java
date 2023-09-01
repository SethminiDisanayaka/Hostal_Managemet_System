package lk.ijse.hostal_management_system.controller;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hostal_management_system.bo.BOFactory;
import lk.ijse.hostal_management_system.bo.custom.StudentBO;
import lk.ijse.hostal_management_system.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentFormController {

    @FXML
    public DatePicker datePicker;
    @FXML
    private TableView<StudentDTO> tblStudent;
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

    public void initialize() {
        txtSyudentId.setEditable(false);

        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        tblDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        makeEditableTxtField(false);
        txtSyudentId.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });


        loadStudentData("");
    }

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
            studentDTO.setStudent_id(idText);

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
        if (!txtSyudentId.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");
            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Not selected !").show();
        }
    }

    @FXML
    void NewButtonOnAction(ActionEvent event) {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        String nextID = generateNextID(studentBO.getCurrentID());
        txtSyudentId.setText(nextID);
        txtStudentName.requestFocus();
    }

    @FXML
    void NextButtonOnAction(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Room_Form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene dashboardScene = new Scene(dashboardParent);
        stage.setScene(dashboardScene);
        stage.show();

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

            if (isValidName() && isValidAddress() && isValidContact()) {
                if (btnSave.getText().equals("Save")) {
                    StudentDTO studentDTO = new StudentDTO(idText, nameText, addressText, contactText, dobText, genderText);
                    Boolean isAdded = studentBO.addStudent(studentDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.INFORMATION, " Student Added ! ").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    }
                }

                if (btnSave.getText().equals("Update")) {
                    StudentDTO studentDTO = new StudentDTO(idText, nameText, addressText, contactText, dobText, genderText);
                    Boolean isUpdated = studentBO.updateStudent(studentDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, " Student Updated ! ").show();
                        clearFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                        clearFields();
                    }
                }
                loadStudentData("");

            } else {
                new Alert(Alert.AlertType.WARNING, "Fill data !").show();
            }
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

    private boolean isValidAddress() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        Matcher matcher = pattern.matcher(txtStudentAddress.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtStudentAddress);
            txtStudentAddress.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidContact() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        Matcher matcher = pattern.matcher(txtContact.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtContact);
            txtContact.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private void loadStudentData(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = studentBO.getStudentData();
        for (StudentDTO std : studentDTOS) {
            if (std.getStudent_id().contains(SearchID) ||
                    std.getName().contains(SearchID) ||
                    std.getAddress().contains(SearchID)) {
                StudentDTO studentDTO = new StudentDTO(std.getStudent_id(),
                        std.getName(), std.getAddress(),
                        std.getContact_no(),
                        std.getDob(),
                        std.getGender());
                list.add(studentDTO);
            }
        }
        tblStudent.setItems(list);
    }

    private void makeEditableTxtField(boolean b) {
        txtSyudentId.setEditable(b);
        txtStudentAddress.setEditable(b);
        txtContact.setEditable(b);
        datePicker.setEditable(b);
    }
    private String generateNextID(String currentID) {
        if (currentID != null && currentID.startsWith("S0")) {
            String[] ids = currentID.split("S0");
            if (ids.length == 2) {
                try {
                    int id = Integer.parseInt(ids[1]);
                    id += 1;
                    return "S0" + id;
                } catch (NumberFormatException e) {
                    // Handle the case where the numeric part is not a valid integer
                    e.printStackTrace();
                }
            }
        }
        return "S01";
    }

    private void setData(StudentDTO newValue) {
        txtSyudentId.setText(newValue.getStudent_id());
        txtStudentName.setText(newValue.getName());
        txtStudentAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact_no());
        datePicker.setValue(LocalDate.parse(newValue.getDob()));
        if (newValue.getGender().equals("Male")) {
            radMale.setSelected(true);
        } else {
            radFemale.setSelected(true);
        }
    }
}
