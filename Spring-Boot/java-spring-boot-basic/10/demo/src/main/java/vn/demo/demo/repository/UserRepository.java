package vn.demo.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.demo.demo.entity.User;
import vn.demo.demo.model.enums.UserRole;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole userRole);
}