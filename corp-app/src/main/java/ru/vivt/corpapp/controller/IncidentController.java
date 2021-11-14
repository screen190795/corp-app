package ru.vivt.corpapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.exceptions.EntityInsertException;
import ru.vivt.corpapp.service.IncidentService;
import ru.vivt.corpapp.utils.IncidentValidator;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/homepage/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getIncidentsList(@RequestParam Map<String, String> params, Model model) {
        List<Incident> incidentsList = this.incidentService.getIncidentsList(params).getContent();
        try {
            if (!incidentsList.isEmpty()) {
                model.addAttribute("incidents", incidentsList);
            }
            return "incidents";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Incident> updateIncident(@PathVariable("id") String id, @RequestBody(required = false) Incident updatedIncident) {

        if (!IncidentValidator.idIsValid(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Incident incidentFromDb = incidentService.getIncident(Long.parseLong(id)).orElse(null);
        if (incidentFromDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            if (updatedIncident.checkNull()) {
                return new ResponseEntity<>(incidentFromDb, HttpStatus.OK);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            incidentFromDb = incidentService.updateIncident(incidentFromDb, updatedIncident);
        } catch (EntityInsertException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(incidentFromDb, HttpStatus.OK);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> getIncidentsCount(@RequestParam Map<String, String> params) {
        long count = this.incidentService.getIncidentsCount(params);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
