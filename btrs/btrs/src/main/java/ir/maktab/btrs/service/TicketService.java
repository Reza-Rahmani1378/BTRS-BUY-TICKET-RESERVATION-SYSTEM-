package ir.maktab.btrs.service;

import ir.maktab.btrs.models.Ticket;
import ir.maktab.btrs.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;


public interface TicketService {

        List<Ticket> findTicketsByStartPointAndEndPointAndDepartureDate(String from, String to, LocalDate departure, Sort sort);
//    List<Ticket> findTicketsByStartPointAndEndPointAndDepartureDateOrderByDepartureDate(String from , String to , LocalDate departure , Sort sort);

        Ticket findTicketById(Long id);

        void deleteTicketById(Long id);

        List<Ticket> findAll();

        Ticket saveInfoTicket(Ticket ticket);

        //

}
