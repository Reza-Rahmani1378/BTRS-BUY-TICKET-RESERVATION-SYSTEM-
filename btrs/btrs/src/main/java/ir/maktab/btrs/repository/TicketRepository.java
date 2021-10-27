package ir.maktab.btrs.repository;

import ir.maktab.btrs.models.Ticket;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketsByStartPointAndEndPointAndDepartureDate(String from, String to, LocalDate departure, Sort sort);

    Ticket findTicketById(Long id);

    void deleteTicketById(Long id);


}
