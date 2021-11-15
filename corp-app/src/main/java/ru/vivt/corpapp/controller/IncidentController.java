package ru.vivt.corpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.entity.MyUserDetails;
import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.User;
import ru.vivt.corpapp.exceptions.EntityInsertException;
import ru.vivt.corpapp.service.IncidentService;
import ru.vivt.corpapp.service.MyUserDetailsService;
import ru.vivt.corpapp.service.ReporterService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/homepage/incidents")
public class IncidentController {
    private final IncidentService incidentService;
    private final ReporterService reporterService;
    private final MyUserDetailsService myUserDetailsService;


    @Autowired
    public IncidentController(IncidentService incidentService, ReporterService reporterService, MyUserDetailsService myUserDetailsService) {
        this.incidentService = incidentService;
        this.reporterService = reporterService;
        this.myUserDetailsService = myUserDetailsService;

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


    @GetMapping(value = "/count")
    public ResponseEntity<Long> getIncidentsCount(@RequestParam Map<String, String> params) {
        long count = this.incidentService.getIncidentsCount(params);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping(value = "/takeToJob/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String takeToJob(@PathVariable(value = "id") Long id, Model model) {
        this.incidentService.takeToJob(id);
        List<Incident> incidentsList = this.incidentService.getIncidentsList();
        model.addAttribute("incidents", incidentsList);
        return "incidents";
    }

    @GetMapping(value = "/setWorkFlow/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Incident updatedIncident = incidentService.getIncident(id).orElseThrow(() -> new IllegalArgumentException("Неверный Id инцидента:" + id));
        model.addAttribute("incident", updatedIncident);
        return "setIncidentWorkFlow";
    }

    @PostMapping("/update/{id}")
    public String updateIncident(@PathVariable("id") long id, Incident updatedIncident, BindingResult result,
                                 Model model) {
        Incident incidentFromDB = incidentService.getIncident(id).orElseThrow(() -> new IllegalArgumentException("Неверный Id инцидента:" + id));
        try {
            this.incidentService.updateIncident(incidentFromDB, updatedIncident);
        } catch (EntityInsertException e) {
            e.printStackTrace();
        }
        List<Incident> incidentsList = this.incidentService.getIncidentsList();
        model.addAttribute("incidents", incidentsList);
        return "incidents";
    }

    @PostMapping("/create")
    public String createIncident(@AuthenticationPrincipal MyUserDetails user, Incident incident, BindingResult result, Model model) {
        User userReporter = this.myUserDetailsService.getUserByUsername(user.getUsername()).orElseThrow(() -> new IllegalArgumentException("пользователь не найден"));
        Reporter reporter = this.reporterService.getReporterByUser(userReporter);
        this.incidentService.createIncident(incident,reporter);
        List<Incident> incidentsList = this.incidentService.getIncidentsList();
        model.addAttribute("incidents", incidentsList);
        return "incidents";
    }


}
