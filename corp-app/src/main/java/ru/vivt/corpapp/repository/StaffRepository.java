package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.Staff;
import ru.vivt.corpapp.entity.User;

import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Long>, JpaSpecificationExecutor<Staff> {
    List<Staff> findByUser(User user);
}