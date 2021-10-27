package ir.maktab.btrs.models;

import ir.maktab.btrs.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Bus.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus extends BaseEntity<Long> {

    public static final String TABLE_NAME = "bus_table";
    public static final String TOTAL_SEAT = "total_seat";

    private String name;

    private String code;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Ticket> tickets;


}
