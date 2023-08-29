package lk.ijse.hostal_management_system.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class Student {

    @Id
    @Column(name = "Student_id")
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    @Column(columnDefinition = "DATE")
    private LocalDate dob;
    private String gender;
}
