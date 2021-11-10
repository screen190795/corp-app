package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vivt.corpapp.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}