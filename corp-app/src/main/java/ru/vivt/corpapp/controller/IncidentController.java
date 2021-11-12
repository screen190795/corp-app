package ru.vivt.corpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.service.IncidentService;

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
        System.out.println(incidentsList);
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

}
