package lk.ijse.hostal_management_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.hostal_management_system.bo.BOFactory;
import lk.ijse.hostal_management_system.bo.custom.ReservationBO;
import lk.ijse.hostal_management_system.dto.CustomDTO;
import lk.ijse.hostal_management_system.dto.ReservationDTO;
import lk.ijse.hostal_management_system.dto.RoomDTO;
import lk.ijse.hostal_management_system.dto.StudentDTO;
import lk.ijse.hostal_management_system.entity.Room;
import lk.ijse.hostal_management_system.entity.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ReservationFormController {

    @FXML
    public ToggleGroup PaymentFilter;
    @FXML
    public ToggleGroup status;
    @FXML
    public RadioButton radAll;
    @FXML
    public RadioButton radPending2;
    @FXML
    public RadioButton radPaid2;
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
    private TableView<CustomDTO> tblReservation;

    @FXML
    private TableView<RoomDTO> tblSelectRoom;

    @FXML
    private TableView<StudentDTO> tblSelectStudent;

    @FXML
    private TableColumn<?, ?> tblcolSNameRes;

    @FXML
    private TextField txtResId;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtStudentId;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.Type.RECEPTION);
    StudentDTO studentDTO;
    RoomDTO roomsDTO;
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
        txtResId.clear();
        txtStudentId.clear();
        txtRoomId.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String idText = txtResId.getText();

            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setRes_id(idText);

            boolean isDeleted = reservationBO.deleteReservation(reservationDTO);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, " Deleted ! ").show();

                loadReservationTable("");
                loadRoomTable("");
                loadStudentTable("");
//
//                newReservationPane.setDisable(true);
//                reservationDetailsPane.setDisable(false);
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        btnReserve.setText("Update");
        btnDelete.setDisable(false);
        radAll.setSelected(true);
    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        String nextID = generateNextID(reservationBO.getCurrentID());
        txtResId.setText(nextID);
        txtRoomId.setText("");
        txtStudentId.setText("");

        datePicker.setValue(LocalDate.now());


        btnDelete.setDisable(true);

        btnReserve.setText("Reserve");
        radAll.setSelected(true);

    }

    @FXML
    void btnreserveOnAction(ActionEvent event) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setRes_id(txtResId.getText());
        reservationDTO.setRes_date(datePicker.getValue());

        RadioButton rb = (RadioButton) status.getSelectedToggle();
        reservationDTO.setStatus(rb.getText());

        try {
            reservationDTO.setRoom(new Room
                    (roomsDTO.getRoom_type_id(),
                            roomsDTO.getType(),
                            roomsDTO.getKey_money(),
                            roomsDTO.getQty()));

            reservationDTO.setStudent(new Student
                    (studentDTO.getStudent_id(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContact_no(),
                            studentDTO.getDob(),
                            studentDTO.getGender()));
        } catch (Exception ex) {
            new Alert(Alert.AlertType.WARNING, " Select / Fill Data ! ").show();
        }

        if (datePicker.getValue() != null) {
            if (btnReserve.getText().equals("Reserve")) {
                boolean isAdded = reservationBO.addReservation(reservationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Added ! ").show();

                    loadReservationTable("");
                    loadRoomTable("");
                    loadStudentTable("");

//                    newReservationPane.setDisable(true);
//                    reservationDetailsPane.setDisable(false);

                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            } else {
                boolean isAdded = reservationBO.updateReservation(reservationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Updated ! ").show();

                    loadReservationTable("");
                    loadRoomTable("");
                    loadStudentTable("");

//                    newReservationPane.setDisable(true);
//                    reservationDetailsPane.setDisable(false);

                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, " Wrong Date ! ").show();
        }
    }


    private void loadReservationTable(String SearchID) {
        ObservableList<CustomDTO> list = FXCollections.observableArrayList();

        ArrayList<CustomDTO> customDTOS = reservationBO.getReservationData();
        for (CustomDTO c : customDTOS) {
            if (c.getStatus().contains(SearchID) ||
                    c.getRes_id().contains(SearchID) ||
                    c.getRoom_type_id().contains(SearchID) ||
                    c.getType().contains(SearchID)) {

                CustomDTO customDTO = new CustomDTO(
                        c.getRes_id(),
                        c.getRes_date(),
                        c.getRoom_type_id(),
                        c.getType(),
                        c.getId(),
                        c.getName(),
                        c.getKey_money(),
                        c.getStatus());

                list.add(customDTO);
            }
        }
        tblReservation.setItems(list);
    }
    private void loadRoomTable(String SearchID) {
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();

        ArrayList<RoomDTO> roomsDTOS = reservationBO.getRoomsData();
        for (RoomDTO std : roomsDTOS) {
            if (std.getQty() > 0) {
                if (std.getRoom_type_id().contains(SearchID) ||
                        std.getKey_money().contains(SearchID) ||
                        std.getType().contains(SearchID)) {
                    RoomDTO roomsDTO = new RoomDTO(
                            std.getRoom_type_id(),
                            std.getType(),
                            std.getKey_money(),
                            std.getQty());
                    list.add(roomsDTO);
                }
            }
        }
        tblSelectRoom.setItems(list);
    }
    private void loadStudentTable(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = reservationBO.getStudentData();
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
        tblSelectStudent.setItems(list);
    }
    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("RS0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "RS0" + id;
        }
        return "RS01";
    }
}
