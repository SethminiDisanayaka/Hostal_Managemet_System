package lk.ijse.hostal_management_system.dao;

import lk.ijse.hostal_management_system.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostal_management_system.dao.custom.impl.StudentDAOImpl;

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
        RESERVATION,
        JOIN_QUERY
    }

    public SuperDAO getDAO(Types types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            default:
                return null;
        }
    }
}
