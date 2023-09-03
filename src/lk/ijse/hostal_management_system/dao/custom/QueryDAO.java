package lk.ijse.hostal_management_system.dao.custom;

import lk.ijse.hostal_management_system.dao.SuperDAO;
import lk.ijse.hostal_management_system.entity.CustomEntity;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    ArrayList<CustomEntity> getData();
}
