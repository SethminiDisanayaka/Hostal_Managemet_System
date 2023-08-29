package lk.ijse.hostal_management_system.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
