package lk.ijse.hostal_management_system.bo;

import lk.ijse.hostal_management_system.bo.custom.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum Type {
        STUDENT,
        ROOM,
        RECEPTION,
        USER
    }

    public SuperBO getBO(Type type) {
        switch (type) {
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
