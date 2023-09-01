package lk.ijse.hostal_management_system.bo.custom;

import lk.ijse.hostal_management_system.bo.SuperBO;
import lk.ijse.hostal_management_system.dto.RoomDTO;

import java.util.ArrayList;

public interface RoomBO extends SuperBO {

    Boolean addRoom(RoomDTO roomsDTO);

    Boolean deleteRoom(RoomDTO roomsDTO);

    ArrayList<RoomDTO> getRoomsData();

    //String getCurrentID();

    Boolean updateRoom(RoomDTO roomsDTO);
}
