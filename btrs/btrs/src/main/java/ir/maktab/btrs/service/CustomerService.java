package ir.maktab.btrs.service;

import ir.maktab.btrs.models.Customer;
import ir.maktab.btrs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface CustomerService {


    Customer saveCustomerInfo(Customer customer);

    Customer findCustomerById(Long id);

}
