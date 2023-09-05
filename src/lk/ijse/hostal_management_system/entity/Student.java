package lk.ijse.hostal_management_system.entity;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private String student_id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "Contact_no")
    private String contactNo;
    @Column(columnDefinition = "DATE", name = "date")
    private String dob;
    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();

    public Student(String id, String name, String address, String contact_no, String dob, String gender) {
        this.student_id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contact_no;
        this.dob = dob;
        this.gender = gender;
    }
}