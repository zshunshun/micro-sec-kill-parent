package com.shun.service;

import com.shun.entity.Manager;

public interface ManagerService {
    Manager login(Manager manager);
    Boolean logout(Manager manager);
}
