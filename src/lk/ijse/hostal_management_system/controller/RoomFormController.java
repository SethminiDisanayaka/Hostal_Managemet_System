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
import lk.ijse.hostal_management_system.bo.custom.RoomBO;
import lk.ijse.hostal_management_system.dto.RoomDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomFormController {

    @FXML
    private TableView<RoomDTO> tblRoom;
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
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRoomTypeID;

    @FXML
    private TextField txtType;

    RoomBO roomsBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.Type.ROOM);


    public void initialize() {
        tblRoomId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(false);
                btnCancel.setDisable(false);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            } else {
                 clearFields();
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(true);
            }
        });

        // Load initial data
        loadRoomData("");
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
    void CancelButtonOnAction(ActionEvent event) {

        clearFields();
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String idText = txtRoomTypeID.getText();

            RoomDTO roomsDTO = new RoomDTO();
            roomsDTO.setRoom_type_id(idText);

            Boolean isAdded = roomsBO.deleteRoom(roomsDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, " Room Deleted ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }

        loadRoomData("");
    }

    @FXML
    void EditButtonOnAction(ActionEvent event) {

        if (!txtRoomTypeID.getText().equals("")) {
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
    void NextButtonOnAction(ActionEvent event) throws IOException {

        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/resources/view/Reservation_Form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene dashboardScene = new Scene(dashboardParent);
        stage.setScene(dashboardScene);
        stage.show();
    }

    @FXML
    void SaveButtonOnAction(ActionEvent event) {

        if (!txtRoomTypeID.getText().equals("") || txtType.getText().equals("") || txtKeyMoney.getText().equals("")) {
            String roomTypeIDText = txtRoomTypeID.getText();
            String typeText = txtType.getText();
            String keyMoneyText = txtKeyMoney.getText();
            int qtyText = Integer.parseInt(txtQty.getText());

            if (isValidRoomTypeID() && isValidType() && isValidKeyMoney() && isValidQTY()) {
                if (btnSave.getText().equals("Save")) {
                    RoomDTO roomsDTO = new RoomDTO(roomTypeIDText, typeText, keyMoneyText, qtyText);
                    Boolean isAdded = roomsBO.addRoom(roomsDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.INFORMATION, " Room Added ! ").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    }
                }

                if (btnSave.getText().equals("Update")) {
                    RoomDTO roomsDTO = new RoomDTO(roomTypeIDText, typeText, keyMoneyText, qtyText);
                    Boolean isUpdated = roomsBO.updateRoom(roomsDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, " Room Updated ! ").show();
                        clearFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                        clearFields();
                    }
                }
                loadRoomData("");

            } else {
                new Alert(Alert.AlertType.WARNING, "Fill data !").show();
            }
        }
    }

    private boolean isValidRoomTypeID() {
        Pattern pattern = Pattern.compile("^(?:RM-)[0-9]{4}$");
        Matcher matcher = pattern.matcher(txtRoomTypeID.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtRoomTypeID);
            txtRoomTypeID.requestFocus();
            shakeUserName.play();
            return false;
        }
    }
    private boolean isValidType() {
        Pattern pattern = Pattern.compile("^(AC|Non-AC|None)$");
        Matcher matcher = pattern.matcher(txtType.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtType);
            txtType.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidKeyMoney() {
        Pattern pattern = Pattern.compile("^[0-9]{3,}$");
        Matcher matcher = pattern.matcher(txtKeyMoney.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtKeyMoney);
            txtKeyMoney.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidQTY() {
        Pattern pattern = Pattern.compile("^[0-9]{1,}$");
        Matcher matcher = pattern.matcher(txtQty.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtQty);
            txtQty.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private void clearFields() {
        txtRoomTypeID.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
    }

    private void loadRoomData(String searchID) {
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();

        try {
            ArrayList<RoomDTO> roomsDTOS = roomsBO.getRoomsData();

            if (roomsDTOS != null) {
                for (RoomDTO std : roomsDTOS) {
                    if (std != null) {
                        String roomTypeId = std.getRoom_type_id();
                        String keyMoney = std.getKey_money();
                        String type = std.getType();

                        if ((roomTypeId != null && roomTypeId.contains(searchID)) ||
                                (keyMoney != null && keyMoney.contains(searchID)) ||
                                (type != null && type.contains(searchID))) {
                            RoomDTO roomsDTO = new RoomDTO(roomTypeId, type, keyMoney, std.getQty());
                            list.add(roomsDTO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblRoom.setItems(list);
    }

    private void makeEditableTxtField(boolean b) {
        txtRoomTypeID.setEditable(b);
        txtType.setEditable(b);
        txtKeyMoney.setEditable(b);
        txtQty.setEditable(b);
    }

    public void NewButtonOnAction(ActionEvent actionEvent) {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        txtRoomTypeID.requestFocus();
        txtRoomTypeID.setText("RM-");
    }
    private void setData(RoomDTO newValue) {
        txtRoomTypeID.setText(newValue.getRoom_type_id());
        txtType.setText(newValue.getType());
        txtKeyMoney.setText(newValue.getKey_money());
        txtQty.setText(String.valueOf(newValue.getQty()));
    }

}
