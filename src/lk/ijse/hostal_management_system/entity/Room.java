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
@Entity
public class Room {
    @Id
    @Column(name = "room_type_id")
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;

    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();
}
