package ir.maktab.btrs.controller;

import ir.maktab.btrs.models.Customer;
import ir.maktab.btrs.models.Ticket;
import ir.maktab.btrs.models.dto.TicketDto;
import ir.maktab.btrs.service.CustomerService;
import ir.maktab.btrs.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/listTickets")
    public String listTickets(@ModelAttribute TicketDto ticketDto, Model model) {

        String from = ticketDto.getStartPoint();
        String to = ticketDto.getEndPoint();
        LocalDate departure = ticketDto.getDepartureDate();
        List<Ticket> tickets = ticketService.findTicketsByStartPointAndEndPointAndDepartureDate(
                from, to, departure, Sort.by(Sort.Direction.ASC, "departureTime"));

        model.addAttribute("ticketsSearch", tickets);
        model.addAttribute("fromTo", "مسیر: " + from + "-" + to);

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//        String localDate = formatter.format(String.valueOf(departure));
        model.addAttribute("departureDate", "تاریخ حرکت: " + departure);

        return "listTicketsSearch";
    }


    @GetMapping("/customerTickets")
    public String customerTickets(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("logedUser");
        Set<Ticket> tickets = customer.getTickets();
        model.addAttribute("ticketsCustomer", tickets);
        model.addAttribute("userType", customer.getUserType());
        return "tickets";

    }

    @RequestMapping("/viewTicket")
    public String viewTicket(@RequestParam(value = "id") Long chooseId, Model model, HttpSession session) {
        Ticket viewTicket = ticketService.findTicketById(chooseId);
        Customer customer = (Customer) session.getAttribute("logedUser");
        String customerName = customer.getFirstName() + " " + customer.getLastName();
        String gender = customer.getGender().name().equals("MALE") ? "مرد" : "زن";
        Map<String, Object> ticketMap = new HashMap<>();
        ticketMap.put("viewTicket", viewTicket);
        ticketMap.put("customerName", customerName);
        ticketMap.put("customGender", gender);
        ticketMap.put("userType", customer.getUserType());
        model.addAllAttributes(ticketMap);
        return "viewTicket";
    }

    @RequestMapping("/deleteTicket")
    public String deleteTicket(@RequestParam(value = "id") Long chooseId, Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("logedUser");

        Ticket ticket = ticketService.findTicketById(chooseId);
        List<Ticket> tickets = ticketService.findAll();
//       Ticket ticket = tickets
//               .stream().filter(t-> t.getId().equals(chooseId)).collect(Collectors.toList()).get(0) ;
        Set<Ticket> collectFilterTickets = customer.getTickets().
                stream().filter(t -> !t.getId().equals(ticket.getId())).collect(Collectors.toSet());
        customer.getTickets().clear();
        customer.getTickets().addAll(collectFilterTickets);
        customerService.saveCustomerInfo(customer);
        ticket.setTotalSeat(ticket.getTotalSeat() + 1);
        ticketService.saveInfoTicket(ticket);
        model.addAttribute("userType", customer.getUserType());
        return "cancellationTicket";

    }
}
