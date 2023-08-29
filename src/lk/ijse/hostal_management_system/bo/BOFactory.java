package lk.ijse.hostal_management_system.bo;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    private enum Type{
        STUDENT,
        ROOM,
        RESERVATION,
        USER
    }
}
