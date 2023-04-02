package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.UserIsNotExistException;
import com.example.demowithtests.util.config.Mapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
//@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean  implements EmployeeController{

    private final Service service;
    private final Mapper mapper;

    public EmployeeControllerBean(Service service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    //Операция сохранения юзера в базу данных
    @Override
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("Controller --> saveEmployee() - start: employeeDto = {}", employeeDto);
        Employee employee = mapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = mapper.employeeToEmployeeDto(service.create(employee));
        log.info("Controller --> saveEmployee() - end: employeeDto = {}", dto);
        return dto;
    }

    //Получение списка юзеров
    @Override
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsers() {
        log.info("Controller --> getAllUsers() - start");
        List<Employee> employees = service.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(
                    mapper.employeeToEmployeeReadDto(employee)
            );
        }
        log.info("Controller --> getAllUsers() - end: employeesReadDto = {}", employeesReadDto);
        return employeesReadDto;
    }

    //Получения юзера по id
    @Override
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable String id) {
        log.info("Controller --> getEmployeeById() - start: id = {}", id);
        EmployeeReadDto empl=mapper.employeeToEmployeeReadDto(
                service.getById(id));
        log.info("Controller --> getEmployeeById() - end: EmployeeReadDto = {}", empl);
        return empl;

    }

    //Обновление юзера
    @Override
    @SneakyThrows
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto)  {
        log.info("Controller --> refreshEmployee() - start: id = {}, employeeDto = {}:", id,employeeDto);
        Integer parseId = Integer.parseInt(id);
        EmployeeReadDto refreshedEmployee=mapper.employeeToEmployeeReadDto(
                service.updateById(parseId, mapper.employeeDtoToEmployee(employeeDto)
                )
        );
        log.info("Controller --> refreshEmployee() - end: EmployeeReadDto = {}", refreshedEmployee);
        return refreshedEmployee;
    }

    //Удаление по id
    @Override
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable String id) {
        log.info("Controller --> removeEmployeeById() - start: id = {}", id);
        Integer parseId = Integer.parseInt(id);
        service.removeById(parseId);
        log.info("Controller --> removeEmployeeById() - end");
    }

    //Удаление всех юзеров
    @Override
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        log.info("Controller --> removeAllUsers() - start");
        service.removeAll();
        log.info("Controller --> removeAllUsers() - end");
    }

    @Override
    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        log.info("Controller --> sendEmailByCountry() - start: country = {}, text = {}", country,text);
        service.sendEmailByCountry(country, text);
        log.info("Controller --> sendEmailByCountry() - end");
    }

    @Override
    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String cities, @RequestBody String text) {
        log.info("Controller --> sendEmailByCity() - start: cities = {}, text = {}", cities,text);
        service.sendEmailByCity(cities, text);
        log.info("Controller --> sendEmailByCity() - end");
    }

    @Override
    @PostMapping("/fillingDataBase/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void fillingDataBase(@PathVariable String quantity) {
        log.info("Controller --> fillingDataBase() - start: quantity = {}", quantity);
        service.fillingDataBase(quantity);
        log.info("Controller --> fillingDataBase() - end:");
    }

    @Override
    @PostMapping("/updateBaseByCountryFully")
    @ResponseStatus(HttpStatus.OK)
    public void updateByCountryFully(@RequestParam String countries) {
        log.info("Controller --> updateByCountryFully() - start: countries = {}", countries);
        service.updaterByCountryFully(countries);
        log.info("Controller --> updateByCountryFully() - end:");
    }

    @Override
    @PostMapping("/updateBaseByCountrySmart")
    @ResponseStatus(HttpStatus.OK)
    public void updateByCountrySmart(@RequestParam String countries) {
        log.info("Controller --> updateByCountrySmart() - start: countries = {}", countries);
        service.updaterByCountrySmart(countries);
        log.info("Controller --> updateByCountryFully() - end:");
    }

    @Override
    @PostMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        log.info("Controller --> replaceNull() - start");
        service.processor();
        log.info("Controller --> replaceNull() - end");
    }

    @Override
    @PostMapping("/sendEmailPhoto")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> sendEmailPhoto() {
        log.info("Controller --> sendEmailPhoto() - start");
        List<Employee> emplList=service.sendMailToEverybody();
        List<EmployeeReadDto> employeeReadDtoList=new ArrayList<>();
        for (Employee empl:emplList){
            employeeReadDtoList.add(mapper.employeeToEmployeeReadDto(empl));
        }
        log.info("Controller --> sendEmailPhoto() - end: List<EmployeeReadDto> = {}",employeeReadDtoList);
        return employeeReadDtoList;
    }

    @Override
    @PostMapping("/sendEmailToCoutriesSwappers")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> sendMailToCountrySwappedEmployees(){
        log.info("Controller --> sendMailToCountrySwappedEmployees() - start");
        List<Employee> emplList=service.sendMailToCountrySwappers();
        List<EmployeeReadDto> employeeReadDtoList=new ArrayList<>();
        for (Employee empl:emplList){
            employeeReadDtoList.add(mapper.employeeToEmployeeReadDto(empl));
        }
        log.info("Controller --> sendMailToCountrySwappedEmployees() - end: List<EmployeeReadDto> = {}",employeeReadDtoList);
        return employeeReadDtoList;
    }
}