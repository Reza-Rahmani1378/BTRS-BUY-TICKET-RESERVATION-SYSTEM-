package ir.maktab.btrs.repository;


import ir.maktab.btrs.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerById(Long id);
}
