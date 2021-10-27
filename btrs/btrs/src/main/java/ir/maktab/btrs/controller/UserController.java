package ir.maktab.btrs.controller;

import ir.maktab.btrs.models.Customer;
import ir.maktab.btrs.models.Ticket;
import ir.maktab.btrs.models.User;
import ir.maktab.btrs.models.dto.CustomerDto;
import ir.maktab.btrs.models.dto.TicketDto;
import ir.maktab.btrs.models.enumeration.UserType;
import ir.maktab.btrs.repository.UserRepository;
import ir.maktab.btrs.service.CustomerService;
import ir.maktab.btrs.service.TicketService;
import ir.maktab.btrs.service.UserService;
import ir.maktab.btrs.util.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
//@ComponentScan("ir.maktab.btrs.service")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/home")
    public String viewHomePage() {
        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUserType(UserType.CUSTOMER);
        userService.createUser(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/header")
    public String specifyUserType(Model model, HttpSession session) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(principal.getUsername());
        UserType userType = user.getUserType();
        session.setAttribute("logedUser", user);
        model.addAttribute("userType", userType);

        return "header";
    }

    @GetMapping("/purchase")
    public String purchaseForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("logedUser");
        UserType userType = user.getUserType();
        model.addAttribute("userType", userType);
        model.addAttribute("ticketDto", new TicketDto());
        return "purchase";
    }

    @RequestMapping("/gender")
    public String selectGender(@RequestParam(value = "id") Long chooseId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("logedUser");
        UserType userType = user.getUserType();
        session.setAttribute("chooseId", chooseId);
        model.addAttribute("userType", userType);
        model.addAttribute("gender", new CustomerDto());
        return "selectGender";

    }

    @PostMapping("/success")
    public String successfully(@ModelAttribute CustomerDto customerDto, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Long chooseId = (Long) session.getAttribute("chooseId");
        Customer customer = (Customer) session.getAttribute("logedUser");
        Ticket buyTicket = ticketService.findTicketById(chooseId);
        buyTicket.setTotalSeat(buyTicket.getTotalSeat() - 1);
        customer.getTickets().add(buyTicket);
        customerService.saveCustomerInfo(customer);
        model.addAttribute("customer", customerDto);
        model.addAttribute("customerType", customerDto.getGender());
        model.addAttribute("fullName", customerDto.getFirstName());
        return "success";
    }


}
