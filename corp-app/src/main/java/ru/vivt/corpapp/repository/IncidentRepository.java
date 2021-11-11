package ru.vivt.corpapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.vivt.corpapp.entity.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository extends CrudRepository<Incident, Long>, JpaSpecificationExecutor<Incident> {

    Optional<Incident> findById(Long id);

    List<Incident> findAll();


    Page<Incident> findAll(Pageable page);


}