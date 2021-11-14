package ru.vivt.corpapp.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.exceptions.EntityInsertException;

import java.util.Map;
import java.util.Optional;

public interface IncidentService {
    Page<Incident> getIncidentsList(Map<String, String> params);

//    long getIncidentsCount(Map<String, String> params);
//
    Incident createIncident(Incident incident);

    Incident updateIncident(Incident incidentFromDB, Incident updatedIncident) throws EntityInsertException;

    void deleteIncident(Long id);

    Optional<Incident> getIncident(Long id);
}
