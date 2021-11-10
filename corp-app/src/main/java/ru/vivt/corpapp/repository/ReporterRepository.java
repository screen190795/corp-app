package ru.vivt.corpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vivt.corpapp.entity.Reporter;

public interface ReporterRepository extends JpaRepository<Reporter, Integer> {
}