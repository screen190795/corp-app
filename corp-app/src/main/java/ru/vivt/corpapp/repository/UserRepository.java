package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vivt.corpapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
