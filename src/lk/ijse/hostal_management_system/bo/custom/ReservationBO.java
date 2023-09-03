package lk.ijse.hostal_management_system.bo.custom;

import lk.ijse.hostal_management_system.bo.SuperBO;
import lk.ijse.hostal_management_system.dto.CustomDTO;
import lk.ijse.hostal_management_system.dto.ReservationDTO;
import lk.ijse.hostal_management_system.dto.RoomDTO;
import lk.ijse.hostal_management_system.dto.StudentDTO;

import java.util.ArrayList;

public interface ReservationBO extends SuperBO {

    String getCurrentID();

    ArrayList<RoomDTO> getRoomsData();

    ArrayList<StudentDTO> getStudentData();

    ArrayList<CustomDTO> getReservationData();

    boolean addReservation(ReservationDTO reservationDTO);

    boolean deleteReservation(ReservationDTO reservationDTO);

    boolean updateReservation(ReservationDTO reservationDTO);
}
