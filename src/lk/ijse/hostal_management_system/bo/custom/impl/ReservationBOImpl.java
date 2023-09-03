package lk.ijse.hostal_management_system.bo.custom.impl;

import lk.ijse.hostal_management_system.bo.custom.ReservationBO;
import lk.ijse.hostal_management_system.dto.CustomDTO;
import lk.ijse.hostal_management_system.dto.ReservationDTO;
import lk.ijse.hostal_management_system.dto.RoomDTO;
import lk.ijse.hostal_management_system.dto.StudentDTO;

import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    @Override
    public String getCurrentID() {
        return null;
    }

    @Override
    public ArrayList<RoomDTO> getRoomsData() {
        return null;
    }

    @Override
    public ArrayList<StudentDTO> getStudentData() {
        return null;
    }

    @Override
    public ArrayList<CustomDTO> getReservationData() {
        return null;
    }

    @Override
    public boolean addReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        return false;
    }
}
