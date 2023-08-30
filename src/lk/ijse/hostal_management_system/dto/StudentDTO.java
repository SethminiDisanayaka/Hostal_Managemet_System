package lk.ijse.hostal_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String contact_no;
    private String dob;
    private String gender;

    public StudentDTO(String studentId, String name, String address, String contactNo, LocalDate dob, String gender) {

    }
}
