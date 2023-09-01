package lk.ijse.hostal_management_system.bo.custom.impl;

import lk.ijse.hostal_management_system.bo.custom.StudentBO;
import lk.ijse.hostal_management_system.dao.FactoryDAO;
import lk.ijse.hostal_management_system.dao.custom.StudentDAO;
import lk.ijse.hostal_management_system.dto.StudentDTO;
import lk.ijse.hostal_management_system.entity.Student;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.STUDENT);

    public Boolean addStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

        return studentDAO.add(student);
    }
    public Boolean deleteStudent(StudentDTO studentDTO) {
        return studentDAO.delete(studentDTO.getStudent_id());
    }

    public ArrayList<StudentDTO> getStudentData() {
        ArrayList<StudentDTO> StudentDTOs = new ArrayList<>();
        ArrayList<Student> studentData = studentDAO.getData();

        for (Student std : studentData) {
            StudentDTOs.add(new StudentDTO(std.getStudentId(),
                    std.getName(), std.getAddress(),
                    std.getContactNo(),
                    std.getDob(),
                    std.getGender()));
        }
        return StudentDTOs;
    }

    public String getCurrentID() {
        return String.valueOf(studentDAO.getCurrentID());
    }

    @Override
    public Boolean updateStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

        return studentDAO.update(student);
    }
}
