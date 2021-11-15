package ru.vivt.corpapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.User;
import ru.vivt.corpapp.repository.ReporterRepository;

@Service
public class ReporterServiceImpl implements ReporterService {
    @Autowired
    final ReporterRepository reporterRepository;

    public ReporterServiceImpl(ReporterRepository reporterRepository) {
        this.reporterRepository = reporterRepository;
    }


    @Override
    public Reporter getReporterByUser(User user) {
        return reporterRepository.findByUser(user).get(0);
    }

}
