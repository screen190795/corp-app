package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vivt.corpapp.entity.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
}