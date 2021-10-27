package ir.maktab.btrs.service.impl;

import ir.maktab.btrs.models.Ticket;
import ir.maktab.btrs.repository.TicketRepository;
import ir.maktab.btrs.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
//
//    @Override
//    public List<Ticket> findTicketsByStartPointAndEndPointAndDepartureDateOrderByDepartureDate(String from, String to, LocalDate departure, Sort sort) {
//        return ticketRepository.findTicketsByStartPointAndEndPointAndDepartureDateOrderByDepartureDate(
//                from, to, departure, sort);
//    }


    @Override
    public List<Ticket> findTicketsByStartPointAndEndPointAndDepartureDate(String from, String to, LocalDate departure, Sort sort) {
        return ticketRepository.findTicketsByStartPointAndEndPointAndDepartureDate(from, to, departure, sort);
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepository.findTicketById(id);
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteTicketById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket saveInfoTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
