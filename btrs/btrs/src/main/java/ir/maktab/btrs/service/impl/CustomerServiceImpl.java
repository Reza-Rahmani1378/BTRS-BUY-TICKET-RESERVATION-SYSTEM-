package ir.maktab.btrs.service.impl;

import ir.maktab.btrs.models.Customer;
import ir.maktab.btrs.repository.CustomerRepository;
import ir.maktab.btrs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer saveCustomerInfo(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }
}
