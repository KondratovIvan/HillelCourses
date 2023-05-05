package com.example.demowithtests.service.workplace;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.domain.passport.Passport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface WorkplaceService {

    Workplace create(Workplace workplace);

//    List<Workplace> getAll();

    Workplace getById(Integer id);

//    Boolean isWorkplaceFull(Integer workplaceId);

     Workplace newWorkplaceOffer( Integer workplaceId);
}
