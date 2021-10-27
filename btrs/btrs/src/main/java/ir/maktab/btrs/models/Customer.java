package ir.maktab.btrs.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "customer_ticket_join_table",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    )
    Set<Ticket> Tickets = new HashSet<>();
}
