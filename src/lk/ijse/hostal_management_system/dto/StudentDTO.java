package lk.ijse.hostal_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String dob;
    private String gender;

}
