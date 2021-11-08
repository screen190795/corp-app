package ru.vivt.corpapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vivt.corpapp.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
