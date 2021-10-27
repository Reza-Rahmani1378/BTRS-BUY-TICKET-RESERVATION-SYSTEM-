package ir.maktab.btrs.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private String startPoint;

    private String endPoint;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

//    @DateTimeFormat(pattern = "HH:mm")
//    private LocalTime departureTime;

}
