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
@Table(name = "student")
@Entity

public class Student {
    @Id
    @Column(name = "Student_id")
    private String studentId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "Contact_no")
    private String contactNo;
    @Column(columnDefinition = "DATE",name = "date")
    private LocalDate dob;
    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();
}
