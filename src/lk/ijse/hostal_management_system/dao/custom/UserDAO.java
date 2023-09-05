package lk.ijse.hostal_management_system.dao.custom;

import lk.ijse.hostal_management_system.dao.CrudDAO;
import lk.ijse.hostal_management_system.entity.User;

public interface UserDAO extends CrudDAO<User> {
    String getUser(String id);

    String getPassword(String id);

    boolean updateUser_Pw(String newUserName, String newPw);
}
