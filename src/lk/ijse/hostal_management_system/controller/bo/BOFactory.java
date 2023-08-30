package lk.ijse.hostal_management_system.controller.bo;

public class BOFactory implements SuperBO {

    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory(){
        return boFactory== null? boFactory= new BOFactory() : boFactory;
    }

    public enum Type {
        STUDENT,
        ROOM,
        RECEPTION
    }
}
