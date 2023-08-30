package lk.ijse.hostal_management_system.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();
}
