package ru.vivt.corpapp.service;

import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.User;

public interface ReporterService {
    Reporter getReporterByUser(User user);
}
