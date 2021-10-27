package ir.maktab.btrs.service;

import ir.maktab.btrs.models.Customer;
import ir.maktab.btrs.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User findByPasswordAndAndUsername(String password, String username);

    User findByUsername(String username);

    void createUser(User user);

    List<User> findAll();


}
