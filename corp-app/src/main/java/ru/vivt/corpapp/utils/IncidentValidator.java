package ru.vivt.corpapp.utils;
import ru.vivt.corpapp.entity.Incident;

import java.util.Calendar;
import java.util.Date;
public class IncidentValidator {



        public static boolean idIsValid(String id) {
            if (!MathUtils.isNumeric(id)) {
                return false;
            }
            if (!MathUtils.isInteger(id, 0)) {
                return false;
            }

            return Integer.parseInt(id) > 0;
        }


        public static boolean titleIsValid(String title) {
            return !title.isEmpty();
        }

        public static boolean enoughParamsToCreateIncident(Incident incident) {
            if (incident.getTitle() == null) {
                return false;
            }
            return incident.getReporter() != null;
        }

    }
