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
@Table(name = "room")
@Entity
public class Room {
    @Id
    @Column(name = "room_type_id")
    private String room_type_id;
    @Column(name = "type")
    private String type;
    @Column(name = "key_money")
    private String key_money;
    @Column(name = "qty")
    private int qty;

    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();

    public Room(String room_type_id, String type, String key_money, int qty) {

    }
}
