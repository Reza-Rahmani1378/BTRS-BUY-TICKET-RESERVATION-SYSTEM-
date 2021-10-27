package ir.maktab.btrs.repository;

import ir.maktab.btrs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPasswordAndUsername(String password, String username);

    User findUserByUsername(String username);

}
