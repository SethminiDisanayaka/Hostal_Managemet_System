package lk.ijse.hostal_management_system.dao;

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

}
