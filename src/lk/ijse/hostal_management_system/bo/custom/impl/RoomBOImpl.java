package lk.ijse.hostal_management_system.bo.custom.impl;

import lk.ijse.hostal_management_system.bo.custom.RoomBO;
import lk.ijse.hostal_management_system.dao.FactoryDAO;
import lk.ijse.hostal_management_system.dao.custom.RoomDAO;
import lk.ijse.hostal_management_system.dto.RoomDTO;
import lk.ijse.hostal_management_system.entity.Room;

import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.ROOM);

    @Override
    public Boolean addRoom(RoomDTO roomsDTO) {
        Room room = new Room(
                roomsDTO.getRoom_type_id(),
                roomsDTO.getType(),
                roomsDTO.getKey_money(),
                roomsDTO.getQty());

        return roomDAO.add(room);
    }

    @Override
    public Boolean deleteRoom(RoomDTO roomsDTO) {
        return roomDAO.delete(roomsDTO.getRoom_type_id());
    }

    @Override
    public ArrayList<RoomDTO> getRoomsData() {
        ArrayList<RoomDTO> RoomDTOs = new ArrayList<>();
        ArrayList<Room> roomData = roomDAO.getData();

        for (Room r : roomData) {
            RoomDTOs.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()));
        }
        return RoomDTOs;
    }

    @Override
    public Boolean updateRoom(RoomDTO roomsDTO) {
        Room room = new Room(
                roomsDTO.getRoom_type_id(),
                roomsDTO.getType(),
                roomsDTO.getKey_money(),
                roomsDTO.getQty());

        return roomDAO.update(room);
    }

    public String getCurrentID() {
        return roomDAO.getCurrentID();
    }
}
