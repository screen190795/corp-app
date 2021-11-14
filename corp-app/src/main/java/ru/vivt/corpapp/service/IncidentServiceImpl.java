package ru.vivt.corpapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vivt.corpapp.controller.IncidentOrder;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.exceptions.EntityInsertException;
import ru.vivt.corpapp.repository.IncidentRepository;

import java.util.Map;
import java.util.Optional;

import static ru.vivt.corpapp.specifications.IncidentSpecification.*;

@Service
public class IncidentServiceImpl implements IncidentService {

    final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public Page<Incident> getIncidentsList(Map<String, String> params) {
        int pageNumber = 0;
        int pageSize = 3;
        String order = IncidentOrder.ID.getFieldName();

        if (params.containsKey("pageNumber")) {
            pageNumber = Integer.parseInt(params.get("pageNumber"));

        }

        if (params.containsKey("pageSize")) {
            pageSize = Integer.parseInt(params.get("pageSize"));

        }

        if (params.containsKey("order")) {
            order = IncidentOrder.valueOf(params.get("order")).getFieldName();
        }

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(order));

        if (params.isEmpty()) {
            return this.incidentRepository.findAll(page);
        }

        if (!params.containsKey("id")) {
            params.put("id", null);
        }
        if (!params.containsKey("title")) {
            params.put("title", "");
        }
        if (!params.containsKey("priority")) {
            params.put("priority", null);
        }

        if (!params.containsKey("status")) {
            params.put("status", null);
        }

        if (!params.containsKey("reporter_id")) {
            params.put("reporter_id", null);
        }
        if (!params.containsKey("assignee_id")) {
            params.put("assignee_id", null);
        }

        if (!params.containsKey("comment")) {
            params.put("race", null);
        }

        return this.incidentRepository.findAll(
                titleContains(params.get("title"))
                        .and(priorityEqualTo(params.get("priority")))
                        .and(reporterIdEqualTo(params.get("reporterId")))
                        .and(assigneeIdEqualTo(params.get("after")))
                        .and(statusEqualTo(params.get("status")))
                        .and(commentContains(params.get("comment"))), page);
    }

    @Override
    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    @Override
    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }

    @Override
    public Optional<Incident> getIncident(Long id) {
        return incidentRepository.findById(id);
    }

    @Override
    public Incident updateIncident(Incident incidentFromDb, Incident updatedIncident) throws EntityInsertException {
        if (incidentFromDb.getTitle() != null && updatedIncident.getTitle() != null) {
            incidentFromDb.setTitle(updatedIncident.getTitle());
        }

        if (updatedIncident.getPriority() != null) {
            incidentFromDb.setPriority(updatedIncident.getPriority());
        }

        if (updatedIncident.getReporter() != null) {
            incidentFromDb.setReporter(updatedIncident.getReporter());
        }
        if (updatedIncident.getAssignee() != null) {
            incidentFromDb.setAssignee(updatedIncident.getAssignee());
        }
        if (updatedIncident.getStatus() != null) {
            incidentFromDb.setStatus(updatedIncident.getStatus());
        }
        if (updatedIncident.getComment() != null) {
            incidentFromDb.setComment(updatedIncident.getComment());
        }
        return incidentRepository.save(incidentFromDb);
    }

    @Override
    public long getIncidentsCount(Map<String, String> params) {
        return getIncidentsList(params).getTotalElements();
    }

}
