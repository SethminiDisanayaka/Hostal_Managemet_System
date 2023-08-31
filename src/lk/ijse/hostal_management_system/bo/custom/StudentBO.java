package lk.ijse.hostal_management_system.bo.custom;

import lk.ijse.hostal_management_system.bo.SuperBO;
import lk.ijse.hostal_management_system.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    Boolean addStudent(StudentDTO studentDTO);

    Boolean deleteStudent(StudentDTO studentDTO);

    ArrayList<StudentDTO> getStudentData();

    String getCurrentID();

    Boolean updateStudent(StudentDTO studentDTO);
}
