package lk.ijse.hostal_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFormController {

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
    private Button btnReserve;

    @FXML
    private DatePicker datePicker;

    @FXML
    private RadioButton radPaid;

    @FXML
    private RadioButton radPending;

    @FXML
    private TableColumn<?, ?> tblCOlResId;

    @FXML
    private TableColumn<?, ?> tblColKeyMoneyRes;

    @FXML
    private TableColumn<?, ?> tblColName;

    @FXML
    private TableColumn<?, ?> tblColQty;

    @FXML
    private TableColumn<?, ?> tblColResDate;

    @FXML
    private TableColumn<?, ?> tblColRoomId;

    @FXML
    private TableColumn<?, ?> tblColRoomIdRes;

    @FXML
    private TableColumn<?, ?> tblColSIdRes;

    @FXML
    private TableColumn<?, ?> tblColStatus;

    @FXML
    private TableColumn<?, ?> tblColStudentId;

    @FXML
    private TableColumn<?, ?> tblColType;

    @FXML
    private TableColumn<?, ?> tblColTypeRes;

    @FXML
    private TableColumn<?, ?> tblColkeyMoney;

    @FXML
    private TableView<?> tblReservation;

    @FXML
    private TableView<?> tblSelectRoom;

    @FXML
    private TableView<?> tblSelectStudent;

    @FXML
    private TableColumn<?, ?> tblcolSNameRes;

    @FXML
    private TextField txtResId;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtStudentId;

    @FXML
    void BackButtonOnAction(ActionEvent event) throws IOException {

        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_Form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene dashboardScene = new Scene(dashboardParent);
        stage.setScene(dashboardScene);
        stage.show();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnreserveOnAction(ActionEvent event) {

    }

}
