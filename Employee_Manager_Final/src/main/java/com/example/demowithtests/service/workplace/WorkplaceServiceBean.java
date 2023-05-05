package com.example.demowithtests.service.workplace;


import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.repository.WorkplaceRepository;
import com.example.demowithtests.util.IncorrectNameEnterException;
import com.example.demowithtests.util.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class WorkplaceServiceBean implements WorkplaceService {

    private final WorkplaceRepository workplaceRepository;
    @Override
    public Workplace create(Workplace workplace) {
//        if(workplace.getName()==null||workplace.getAddress()==null){
//            throw new IncorrectDataEnterException();
//        }
        if(!workplace.getName().matches("[a-zA-Z]+")){
            throw new IncorrectNameEnterException();
        }
        else {
            log.debug("Service ==> create() - start: workplace = {}", workplace);
            workplace.setIsActive(Boolean.TRUE);
            Workplace savedWorkplace = workplaceRepository.save(workplace);
            log.debug("Service ==> create() - end: workplace = {}", savedWorkplace);
            return savedWorkplace;
        }
    }

    @Override
    public Workplace getById(Integer id) {
        log.debug("Service ==> getById() - start: id = {}", id);
        var workplace = workplaceRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        log.debug("Service ==> getById() - end: workplace = {}", workplace);
        return workplace;
    }

    @Override
    public Workplace newWorkplaceOffer(Integer workplaceId){
        log.debug("Service ==> newWorkplaceOffer() - start: id = {}", workplaceId);
        List<Workplace> allWorkplaces=workplaceRepository.findAll();
        if (isWorkplaceFull(workplaceId)){
            for (Workplace wp: allWorkplaces) {
                if(!isWorkplaceFull(wp.getId())){
                    log.debug("Service ==> newWorkplaceOffer() - end: Workplace = {}", wp);
                    return wp;
                }
            }
        }
        else {
            Workplace checkedWorkplace=allWorkplaces.get(workplaceId);
            log.debug("Service ==> newWorkplaceOffer() - end: Workplace = {}", checkedWorkplace);
            return checkedWorkplace;
        }
        log.debug("Service ==> getById() - end");
        return null;
    }


    public Boolean isWorkplaceFull(Integer workplaceId) {
        log.debug("Service ==> isWorkplaceFull() - start: id = {}", workplaceId);
        List<Workplace> allWorkplaces=workplaceRepository.findAll();
        Workplace checkingWorkplace=allWorkplaces.get(workplaceId);
        Set<Employee> employees=checkingWorkplace.getEmployees();
        List<Employee> emplopyeesList=new ArrayList<>(employees);
        if (emplopyeesList.size()>5){
            log.debug("Service ==> isWorkplaceFull() - end: Boolean = {}", true);
            return true;
        }
        else {
            log.debug("Service ==> isWorkplaceFull() - end: Boolean = {}", false);
            return false;
        }
    }
}
