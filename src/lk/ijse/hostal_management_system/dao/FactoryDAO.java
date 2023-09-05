package lk.ijse.hostal_management_system.dao;

import lk.ijse.hostal_management_system.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostal_management_system.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostal_management_system.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostal_management_system.dao.custom.impl.UserDAOImpl;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    public FactoryDAO() {
    }

    public static FactoryDAO getFactoryDAO(){
        return factoryDAO == null ? factoryDAO = new FactoryDAO() : factoryDAO;
    }

    public enum Types{
        STUDENT,
        ROOM,
        RECEPTION,
        JOIN_QUERY,
        USER
    }

    public SuperDAO getDAO(Types types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RECEPTION:
                return new ReservationDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
