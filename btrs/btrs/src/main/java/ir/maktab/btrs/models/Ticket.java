package ir.maktab.btrs.models;

import ir.maktab.btrs.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = Ticket.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseEntity<Long> {


    public static final String TABLE_NAME = "ticket_table";
    public static final String TRAVEL_ID = "travel_id";
    public static final String TOTAL_SEAT = "total_seat";


    @ManyToOne
    private Bus bus;

    @Column(name = Ticket.TRAVEL_ID)
    private Long travelId;

    private String startPoint;

    private String endPoint;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    private Long totalSeat;


}
