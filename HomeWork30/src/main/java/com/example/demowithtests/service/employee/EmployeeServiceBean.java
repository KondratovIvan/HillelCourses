package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class EmployeeServiceBean implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceBean(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

   // @SneakyThrows
    @Override
    public Employee create(Employee employee) {
        log.debug("EmployeeService --> create() - start: employee = {}", employee);
        if (employeeRepository.findEmployeeByEmail(employee.getEmail()) == null) {
            if (employee.getEmail() == null) {
                throw new EmailAbsentException();
            }
            return employeeRepository.save(employee);
        }
        Employee newEmployee= employeeRepository.save(employee);
        log.debug("EmployeeService --> create() - end: employee = {}", newEmployee);
        return newEmployee;
    }

    @Override
    public List<Employee> getAll() {
        log.debug("EmployeeService --> getAll() - start:");
        if (employeeRepository.findAll().size() > 0) {
            if (employeeRepository.findAll().size() == employeeRepository.findEmployeeByIsDeletedIsTrue().size()) {
                throw new ListWasDeletedException();
            }
            List<Employee> foundAll= employeeRepository.findAll();
            log.debug("EmployeeService --> getAll() - end: List<Employee> = {}", foundAll);
            return foundAll;
        }
        throw new ListHasNoAnyElementsException();

    }

    @Override
    public Employee getById(String id) {
        try {
            log.debug("EmployeeService --> getById() - start: id = {}", id);
            Integer employeeId = Integer.parseInt(id);
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(IdIsNotExistException::new);
            if (employee.getIsDeleted()) {
                throw new ResourceWasDeletedException();
            }
            log.debug("EmployeeService --> getById() - end: employee = {}", employee);
            return employee;
        } catch (NumberFormatException exception) {
            throw new WrongDataException();
        }
    }

    //@SneakyThrows
    @Override
    public Employee updateById(Integer id, Employee employee) throws UserIsNotExistException {
        log.debug("EmployeeService --> updateById() - start: id = {}, employee = {}", id, employee);
        Employee updatedEmpl= employeeRepository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    return employeeRepository.save(entity);
                })
                .orElseThrow(UserIsNotExistException::new);
        log.debug("EmployeeService --> updateById() - end: employee = {}", updatedEmpl);
        return updatedEmpl;
    }

    @Override
    public void removeById(Integer id) {
        log.debug("EmployeeService --> removeById() - start: id = {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(IdIsNotExistException::new);
        if (employee.getDeleted()) throw new UserAlreadyDeletedException();
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
        log.debug("EmployeeService --> removeById() - end: employee = {}", employee);
    }

    @Override
    public void removeAll() {
        log.debug("EmployeeService --> removeAll() - start");
        if (employeeRepository.findAll().size() > 0) {
            if (employeeRepository.findAll().size() == employeeRepository.findEmployeeByIsDeletedIsTrue().size()) {
                throw new ListWasDeletedException();
            }
            List<Employee> base = employeeRepository.findAll();
            for (Employee employee : base) {
                employee.setIsDeleted(true);
            }
            log.debug("EmployeeService --> removeAll() - end");
        }
        throw new ListHasNoAnyElementsException();


    }


    public void mailSender(List<String> emails, String text) {
        log.info("Emails sended");
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        log.debug("EmployeeService --> sendEmailByCountry() - start: country = {}, text = {}",country,text);
        List<Employee> employees = employeeRepository.findEmployeeByCountry(country);
        mailSender(getterEmailsOfEmployees(employees), text);
        log.debug("EmployeeService --> sendEmailByCountry() - end: List<Employee> = {}",employees);
        return employees;
    }

    public List<Employee> sendEmailByCity(String citiesString, String text) {
        log.debug("EmployeeService --> sendEmailByCity() - start: citiesString = {}, text = {}",citiesString,text);
        String[] citiesArray = citiesString.split(",");
        List<String> citiesList = Arrays.asList(citiesArray);
        List<Employee> employees = new ArrayList<>();
        for (String city : citiesList) {
            List<Employee> employeesByCity = employeeRepository.findEmployeeByAddresses(city);
            employees.addAll(employeesByCity);
        }
        mailSender(getterEmailsOfEmployees(employees), text);
        log.debug("EmployeeService --> sendEmailByCity() - end: List<Employee> = {}",employees);
        return employees;
    }

    @Override
    public void fillingDataBase(String quantityString) {
        log.debug("EmployeeService --> fillingDataBase() - start: citiesString = {}",quantityString);
        int quantity = Integer.parseInt(quantityString);
        for (int i = 0; i <= quantity; i++) {
            employeeRepository.save(createrEmployee("name", "country", "email"));
        }
        log.debug("EmployeeService --> fillingDataBase() - end");
    }


    @Override
    public void updaterByCountryFully(String countries) {
        log.debug("EmployeeService --> updaterByCountryFully() - start: countries = {}",countries);
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee:employees) {
            employee.setCountry(randomCountry(countries));
            employeeRepository.save(employee);
        }
        log.debug("EmployeeService --> fillingDataBase() - end");
    }

    @Override
    @Transactional
    public void updaterByCountrySmart(String countries) {
        log.debug("EmployeeService --> updaterByCountryFully() - start: countries = {}",countries);
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            String newCountry = randomCountry(countries);
            if (!employee.getCountry().equals(newCountry)) {
                employee.setCountry(newCountry);
                employeeRepository.save(employee);
            }
        }
        log.debug("EmployeeService --> updaterByCountryFully() - end");
    }

    @Override
    public List<Employee> processor() {
        log.debug("EmployeeService --> updaterByCountryFully() - start");
        //log.info("replace null  - start");
        List<Employee> replaceNull = employeeRepository.findEmployeeByIsDeletedNull();
        //log.info("replace null after replace: " + replaceNull);
        for (Employee emp : replaceNull) {
            emp.setIsDeleted(Boolean.FALSE);
        }
        //log.info("replaceNull = {} ", replaceNull);
        //log.info("replace null  - end:");
        List<Employee> replacedEmployees= employeeRepository.saveAll(replaceNull);
        log.debug("EmployeeService --> updaterByCountryFully() - end: List<Employee> = {}",replacedEmployees);
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
        List<Employee> oldPhotoOwners= employeeRepository.findEmployeeByLatePhoto();
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
        log.debug("EmployeeService --> sendMailToEverybody() - start");
        List<Employee> usersReceivedMails=processorPhoto();
        photoMailSender(usersReceivedMails,"123");
        log.debug("EmployeeService --> sendMailToEverybody() - end: List<Employee> = {}",usersReceivedMails);
        return usersReceivedMails;
    }


    public List<Employee>  sendMailToCountrySwappedEmployees(){
        List<Employee> employeeList= employeeRepository.findEmployeeWhoChangedCountry();
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
        log.debug("EmployeeService --> sendMailToCountrySwappedEmployees() - start");
        List<Employee> employeeList=sendMailToCountrySwappedEmployees();
        countrySwappersMailSender(employeeList,"Dont be upset! You got a sale! ");
        log.debug("EmployeeService --> sendMailToCountrySwappedEmployees() - end: List<Employee> = {}",employeeList);
        return employeeList;
    }
}
