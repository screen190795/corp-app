package ru.vivt.corpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.service.IncidentService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/homepage/incidents")
public class IncidentController {
    private final IncidentService incidentService;

    @Autowired
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

    @PostMapping(value = "/takeToJob/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String takeToJob(@RequestParam Map<String, String> params, @PathVariable(value = "id") Long id, Model model) {
        this.incidentService.takeToJob(id);
        List<Incident> incidentsList = this.incidentService.getIncidentsList();
        model.addAttribute("incidents", incidentsList);
        return "incidents";
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> getIncidentsCount(@RequestParam Map<String, String> params) {
        long count = this.incidentService.getIncidentsCount(params);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
