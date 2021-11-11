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


//    @Override
//    public Incident createIncident(Incident Incident) {
//        Incident.setLevel(Incident.countLevel(Incident.getExperience()));
//        Incident.setUntilNextLevel(Incident.countUntilLevel(Incident.getExperience(), Incident.getLevel()));
//        if (Incident.getBanned() == null) {
//            Incident.setBanned(false);
//        }
//        return IncidentRepository.save(Incident);
//    }

//    @Override
//    public Incident updateIncident(Incident IncidentFromDb, Incident updatedIncident) throws EntityInsertException {
//        if (IncidentFromDb.getName() != null && updatedIncident.getName() != null) {
//            if (!IncidentValidator.nameIsValid(updatedIncident.getName())) {
//                throw new EntityInsertException();
//            }
//            IncidentFromDb.setName(updatedIncident.getName());
//        }
//        if (IncidentFromDb.getTitle() != null && updatedIncident.getTitle() != null) {
//            if (!IncidentValidator.titleIsValid(updatedIncident.getTitle())) {
//                throw new EntityInsertException();
//            }
//            IncidentFromDb.setTitle(updatedIncident.getTitle());
//        }
//        if (IncidentFromDb.getRace() != null && updatedIncident.getRace() != null) {
//            IncidentFromDb.setRace(updatedIncident.getRace());
//        }
//        if (IncidentFromDb.getProfession() != null && updatedIncident.getProfession() != null) {
//            IncidentFromDb.setProfession(updatedIncident.getProfession());
//        }
//        if (IncidentFromDb.getBirthday() != null && updatedIncident.getBirthday() != null) {
//            if (!IncidentValidator.birthDayIsValid(updatedIncident.getBirthday().getTime())) {
//                throw new EntityInsertException();
//            }
//            IncidentFromDb.setBirthday(updatedIncident.getBirthday());
//        }
//        if (IncidentFromDb.getBanned() != null && updatedIncident.getBanned() != null) {
//            IncidentFromDb.setBanned(updatedIncident.getBanned());
//        }
//        if (IncidentFromDb.getExperience() != null && updatedIncident.getExperience() != null) {
//            if (!IncidentValidator.experienceIsValid(updatedIncident.getExperience())) {
//                throw new EntityInsertException();
//            }
//            IncidentFromDb.setExperience(updatedIncident.getExperience());
//            IncidentFromDb.setLevel(Incident.countLevel(IncidentFromDb.getExperience()));
//            IncidentFromDb.setUntilNextLevel((Incident.countUntilLevel(IncidentFromDb.getExperience(), IncidentFromDb.getLevel())));
//
//        }
//        if (IncidentFromDb.getUntilNextLevel() != null && updatedIncident.getUntilNextLevel() != null) {
//            IncidentFromDb.setUntilNextLevel(updatedIncident.getUntilNextLevel());
//        }
//        return IncidentRepository.save(IncidentFromDb);
//    }
//
//    @Override
//    public void deleteIncident(Long id) {
//        IncidentRepository.deleteById(id);
//    }


}
