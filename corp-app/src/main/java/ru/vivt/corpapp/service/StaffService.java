package ru.vivt.corpapp.service;

import ru.vivt.corpapp.entity.Staff;
import ru.vivt.corpapp.entity.User;

public interface StaffService {
    Staff getStaffByUser(User user);
}
