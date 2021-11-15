package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.User;

import java.util.List;

public interface ReporterRepository extends CrudRepository<Reporter, Long>, JpaSpecificationExecutor<Reporter> {
    List<Reporter> findByUser(User user);
}