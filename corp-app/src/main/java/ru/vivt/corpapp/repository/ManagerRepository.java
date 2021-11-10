package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vivt.corpapp.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}