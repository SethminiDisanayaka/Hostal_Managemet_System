package lk.ijse.hostal_management_system.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reservation")

public class Reservation {
    @Id
    private String res_id;
    @Column(columnDefinition = "DATE")
    private String res_date;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room room;
}
