package ru.vivt.corpapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vivt.corpapp.entity.Reporter;
import ru.vivt.corpapp.entity.Staff;
import ru.vivt.corpapp.entity.User;
import ru.vivt.corpapp.repository.ReporterRepository;
import ru.vivt.corpapp.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    @Override
    public Staff getStaffByUser(User user) {
        return staffRepository.findByUser(user).get(0);
    }

}
