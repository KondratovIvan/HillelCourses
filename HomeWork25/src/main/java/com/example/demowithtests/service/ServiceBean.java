package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.AccessException;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.ResourceWasDeletedException;
import com.example.demowithtests.util.WrongArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        try {
            return repository.findAll();
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException();
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public Employee getById(Integer id) {
        if (id == null) {
            throw new WrongArgumentException();
        }
        var employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceNotFoundException::new);
        if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        try {
            return repository.findById(id)
                    .map(entity -> {
                        entity.setName(employee.getName());
                        entity.setEmail(employee.getEmail());
                        entity.setCountry(employee.getCountry());
                        return repository.save(entity);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException();
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public void removeById(Integer id) {
        try {
            var employee = repository.findById(id)
                    // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                    .orElseThrow(ResourceWasDeletedException::new);
            employee.setIsDeleted(true);
            repository.save(employee);
        } catch (DataAccessException e) {
            throw new AccessException();
        }
    }

    @Override
    public void removeAll() {
        try {
            repository.deleteAll();
        } catch (DataAccessException e) {
            throw new AccessException();
        }


    }

    @Override
    public List<Employee> processor() {
        log.info("replace null  - start");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        log.info("replace null after replace: " + replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        log.info("replaceNull = {} ", replaceNull);
        log.info("replace null  - end:");
        return repository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        List<Employee> employees = repository.findEmployeeByCountry(country);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        List<Employee> employees = repository.findEmployeeByAddresses(city);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByStreet(String street, String text) {
        List<Employee> employees = repository.findEmployeeByStreet(street);
        mailSender(extracted(employees), text);
        return employees;
    }


    private static List<String> extracted(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        //Arrays.asList();
        for (Employee emp : employees) {
            emails.add(emp.getEmail());
        }
        return emails;
    }


    public void mailSender(List<String> emails, String text) {
        if(emails.isEmpty()){
            log.info("No users who live in this street");
        }
        else {
            log.info("Emails were successfully sent");
        }
    }

    public List<String> countryGenerator(){
        List<String> countryList=Arrays.asList("Germany","Poland","Ukraine","USA","Japan","China","Italy","Spain","Great Britain","Brazil");
        List<String> ourCountries=new ArrayList<>();
        for (int i=0;i<=10;i++){
            ourCountries.add(countryList.get((int)(Math.random()*10)));
        }
        return ourCountries;
    };

@Transactional
    public void databaseFiller(Integer amount){
        for (int j=0; j<amount;j++){
            for (int i=0; i<10; i++){
                Employee employee=new Employee(null,"TestEmployee",null,countryGenerator().get(i),Boolean.FALSE,null);
                repository.save(employee);
            }
        }
    }

    @Transactional
    public void databaseUpdater(Integer amount, String country){
        List<Employee> emplList=repository.findAll();
        for (int i=0;i<amount;i++) {
           Employee empl=new Employee(emplList.get(i).getId(),emplList.get(i).getName(),emplList.get(i).getEmail(),country,emplList.get(i).getIsDeleted(),null);
           repository.save(empl);
        }
    }
    @Transactional
    public void databaseSQLUpdater(Integer amount, String country){
        List<Employee> elmplList=repository.findEmployeeById(amount);
        for (Employee empl:elmplList) {
            empl.setCountry(country);
        }
        repository.saveAll(elmplList);
    }
    @Transactional
    public void databaseSQLUpdaterUpd(Integer startId,Integer finishId, String country){
        List<Employee> elmplList=repository.findEmployeeByIdUpd(startId,finishId);
        for (Employee empl:elmplList) {
            empl.setCountry(country);
        }
        repository.saveAll(elmplList);
    }

}
