package lk.ijse.hostal_management_system.bo.custom;

import lk.ijse.hostal_management_system.bo.SuperBO;

public interface UserBO extends SuperBO {
    String getUser(String id);

    String getPassword(String id);

    boolean updateUser_Pw(String newUserName, String newPw);
}
