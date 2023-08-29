package lk.ijse.hostal_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<?, ?> tblKeyMoney;

    @FXML
    private TableColumn<?, ?> tblQty;

    @FXML
    private TableColumn<?, ?> tblRoomId;

    @FXML
    private TableColumn<?, ?> tblType;

    @FXML
    private TextField txtSyudentId;

    @FXML
    private TextField txtSyudentId1;

    @FXML
    private TextField txtSyudentId2;

    @FXML
    private TextField txtSyudentId3;

    @FXML
    void BackButtonOnAction(ActionEvent event) throws IOException {

        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene dashboardScene = new Scene(dashboardParent);
        stage.setScene(dashboardScene);
        stage.show();
    }

    @FXML
    void CancelButtonOnAction(ActionEvent event) {

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

}
