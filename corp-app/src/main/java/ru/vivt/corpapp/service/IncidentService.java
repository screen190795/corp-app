package ru.vivt.corpapp.service;

import org.springframework.data.domain.Page;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.Staff;
import ru.vivt.corpapp.exceptions.EntityInsertException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IncidentService {
    Page<Incident> getIncidentsList(Map<String, String> params);
    List<Incident> getIncidentsList();

    long getIncidentsCount(Map<String, String> params);

    Incident createIncident(Incident incident, Reporter reporter);

    Incident updateIncident(Incident incidentFromDB, Incident updatedIncident) throws EntityInsertException;

    void deleteIncident(Long id);

    Incident takeToJob(Long id, Staff staff);

    Optional<Incident> getIncident(Long id);
}
