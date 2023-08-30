package lk.ijse.hostal_management_system.controller.bo.custom.impl;

import lk.ijse.hostal_management_system.controller.bo.custom.StudentBO;
import lk.ijse.hostal_management_system.dao.FactoryDAO;
import lk.ijse.hostal_management_system.dao.custom.StudentDAO;
import lk.ijse.hostal_management_system.dto.StudentDTO;
import lk.ijse.hostal_management_system.entity.Student;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.STUDENT);


    public Boolean addStudent(StudentDTO studentDTO){
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

        return studentDAO.add(student);
    }


    public Boolean deleteStudent(StudentDTO studentDTO){
        return studentDAO.delete(studentDTO.getId());
    }

    public ArrayList<StudentDTO> getStudentData(){
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        ArrayList<Student> studentData = studentDAO.getData();

        for(Student std : studentData){
            studentDTOS.add(new StudentDTO(std.getStudentId(),
                    std.getName(),
                    std.getAddress(),
                    std.getContactNo(),
                    std.getDob(),
                    std.getGender()
                    ));
        }
        return studentDTOS;
    }

    public String getCurrentID(){
        return studentDAO.getCurrentID();
    }

    @Override
    public Boolean updateStudent(StudentDTO studentDTO){
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

         return studentDAO.update(student);
    }
}
