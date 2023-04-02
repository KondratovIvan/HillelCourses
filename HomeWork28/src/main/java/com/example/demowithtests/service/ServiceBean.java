package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {
    private final Repository repository;

    public ServiceBean(Repository repository) {
        this.repository = repository;
    }

   // @SneakyThrows
    @Override
    public Employee create(Employee employee) {
        log.debug("Service --> create() - start: employee = {}", employee);
        if (repository.findEmployeeByEmail(employee.getEmail()) == null) {
            if (employee.getEmail() == null) {
                throw new EmailAbsentException();
            }
            return repository.save(employee);
        }
        Employee newEmployee=repository.save(employee);
        log.debug("Service --> create() - end: employee = {}", newEmployee);
        return newEmployee;
    }

    @Override
    public List<Employee> getAll() {
        log.debug("Service --> getAll() - start:");
        if (repository.findAll().size() > 0) {
            if (repository.findAll().size() == repository.findEmployeeByIsDeletedIsTrue().size()) {
                throw new ListWasDeletedException();
            }
            List<Employee> foundAll=repository.findAll();
            log.debug("Service --> getAll() - end: List<Employee> = {}", foundAll);
            return foundAll;
        }
        throw new ListHasNoAnyElementsException();

    }

    @Override
    public Employee getById(String id) {
        try {
            log.debug("Service --> getById() - start: id = {}", id);
            Integer employeeId = Integer.parseInt(id);
            Employee employee = repository.findById(employeeId)
                    .orElseThrow(IdIsNotExistException::new);
            if (employee.getIsDeleted()) {
                throw new ResourceWasDeletedException();
            }
            log.debug("Service --> getById() - end: employee = {}", employee);
            return employee;
        } catch (NumberFormatException exception) {
            throw new WrongDataException();
        }
    }

    //@SneakyThrows
    @Override
    public Employee updateById(Integer id, Employee employee) throws UserIsNotExistException {
        log.debug("Service --> updateById() - start: id = {}, employee = {}", id, employee);
        Employee updatedEmpl=repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    return repository.save(entity);
                })
                .orElseThrow(UserIsNotExistException::new);
        log.debug("Service --> updateById() - end: employee = {}", updatedEmpl);
        return updatedEmpl;
    }

    @Override
    public void removeById(Integer id) {
        log.debug("Service --> removeById() - start: id = {}", id);
        Employee employee = repository.findById(id)
                .orElseThrow(IdIsNotExistException::new);
        if (employee.getDeleted()) throw new UserAlreadyDeletedException();
        employee.setIsDeleted(true);
        repository.save(employee);
        log.debug("Service --> removeById() - end: employee = {}", employee);
    }

    @Override
    public void removeAll() {
        log.debug("Service --> removeAll() - start");
        if (repository.findAll().size() > 0) {
            if (repository.findAll().size() == repository.findEmployeeByIsDeletedIsTrue().size()) {
                throw new ListWasDeletedException();
            }
            List<Employee> base = repository.findAll();
            for (Employee employee : base) {
                employee.setIsDeleted(true);
            }
            log.debug("Service --> removeAll() - end");
        }
        throw new ListHasNoAnyElementsException();


    }


    public void mailSender(List<String> emails, String text) {
        log.info("Emails sended");
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        log.debug("Service --> sendEmailByCountry() - start: country = {}, text = {}",country,text);
        List<Employee> employees = repository.findEmployeeByCountry(country);
        mailSender(getterEmailsOfEmployees(employees), text);
        log.debug("Service --> sendEmailByCountry() - end: List<Employee> = {}",employees);
        return employees;
    }

    public List<Employee> sendEmailByCity(String citiesString, String text) {
        log.debug("Service --> sendEmailByCity() - start: citiesString = {}, text = {}",citiesString,text);
        String[] citiesArray = citiesString.split(",");
        List<String> citiesList = Arrays.asList(citiesArray);
        List<Employee> employees = new ArrayList<>();
        for (String city : citiesList) {
            List<Employee> employeesByCity = repository.findEmployeeByAddresses(city);
            employees.addAll(employeesByCity);
        }
        mailSender(getterEmailsOfEmployees(employees), text);
        log.debug("Service --> sendEmailByCity() - end: List<Employee> = {}",employees);
        return employees;
    }

    @Override
    public void fillingDataBase(String quantityString) {
        log.debug("Service --> fillingDataBase() - start: citiesString = {}",quantityString);
        int quantity = Integer.parseInt(quantityString);
        for (int i = 0; i <= quantity; i++) {
            repository.save(createrEmployee("name", "country", "email"));
        }
        log.debug("Service --> fillingDataBase() - end");
    }


    @Override
    public void updaterByCountryFully(String countries) {
        log.debug("Service --> updaterByCountryFully() - start: countries = {}",countries);
        List<Employee> employees = repository.findAll();
        for (Employee employee:employees) {
            employee.setCountry(randomCountry(countries));
            repository.save(employee);
        }
        log.debug("Service --> fillingDataBase() - end");
    }

    @Override
    @Transactional
    public void updaterByCountrySmart(String countries) {
        log.debug("Service --> updaterByCountryFully() - start: countries = {}",countries);
        List<Employee> employees = repository.findAll();
        for (Employee employee : employees) {
            String newCountry = randomCountry(countries);
            if (!employee.getCountry().equals(newCountry)) {
                employee.setCountry(newCountry);
                repository.save(employee);
            }
        }
        log.debug("Service --> updaterByCountryFully() - end");
    }

    @Override
    public List<Employee> processor() {
        log.debug("Service --> updaterByCountryFully() - start");
        //log.info("replace null  - start");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        //log.info("replace null after replace: " + replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        //log.info("replaceNull = {} ", replaceNull);
        //log.info("replace null  - end:");
        List<Employee> replacedEmployees=repository.saveAll(replaceNull);
        log.debug("Service --> updaterByCountryFully() - end: List<Employee> = {}",replacedEmployees);
        return replacedEmployees;
    }



    @Override
    public String randomCountry(String countriesString) {
        /*List<String> countries = List.of(countriesString.split(","));
        int randomIndex = (int) (Math.random() * countries.size());
        return countries.get(randomIndex);*/
        return countriesString;
    }

    private static List<String> getterEmailsOfEmployees(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        for (Employee employee : employees) {
            emails.add(employee.getEmail());
        }
        return emails;
    }

    @Override
    public Employee createrEmployee(String name, String country, String email) {
        return new Employee(name, country, email);
    }



    public List<Employee> processorPhoto() {
        List<Employee> oldPhotoOwners=repository.findEmployeeByLatePhoto();
        return oldPhotoOwners;
    }

    public void photoMailSender(List<Employee> oldPhotoOwners, String text){
        if(oldPhotoOwners.isEmpty()){
            log.info("No owners of old photos");
        }
        else {
            log.info("Emails were successfully sent");
        }
    }
    @Override
    public List<Employee> sendMailToEverybody(){
        log.debug("Service --> sendMailToEverybody() - start");
        List<Employee> usersReceivedMails=processorPhoto();
        photoMailSender(usersReceivedMails,"123");
        log.debug("Service --> sendMailToEverybody() - end: List<Employee> = {}",usersReceivedMails);
        return usersReceivedMails;
    }


    public List<Employee>  sendMailToCountrySwappedEmployees(){
        List<Employee> employeeList=repository.findEmployeeWhoChangedCountry();
        return employeeList;
    }
    public void countrySwappersMailSender(List<Employee> employeeList,String mailText){
        if(employeeList.isEmpty()){
            log.info("No employee who swapped country");
        }
        else {
            log.info("Emails were successfully sent");
        }
    }
    @Override
    public List<Employee> sendMailToCountrySwappers(){
        log.debug("Service --> sendMailToCountrySwappedEmployees() - start");
        List<Employee> employeeList=sendMailToCountrySwappedEmployees();
        countrySwappersMailSender(employeeList,"Dont be upset! You got a sale! ");
        log.debug("Service --> sendMailToCountrySwappedEmployees() - end: List<Employee> = {}",employeeList);
        return employeeList;
    }
}
