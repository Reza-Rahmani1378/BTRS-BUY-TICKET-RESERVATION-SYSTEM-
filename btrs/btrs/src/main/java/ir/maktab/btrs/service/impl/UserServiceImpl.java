package ir.maktab.btrs.service.impl;

import ir.maktab.btrs.models.Customer;
import ir.maktab.btrs.models.User;
import ir.maktab.btrs.repository.UserRepository;
import ir.maktab.btrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public User findByPasswordAndAndUsername(String password, String username) {
        return userRepository.findByPasswordAndUsername(password, username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
