package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.UserIsNotExistException;
import com.example.demowithtests.util.config.Mapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
//@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
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
        Employee employee = mapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = mapper.employeeToEmployeeDto(service.create(employee));
        return dto;
    }

    //Получение списка юзеров
    @Override
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsers() {
        List<Employee> employees = service.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(
                    mapper.employeeToEmployeeReadDto(employee)
            );
        }
        return employeesReadDto;
    }

    //Получения юзера по id
    @Override
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable String id) {
        return mapper.employeeToEmployeeReadDto(
                service.getById(id)
        );
    }

    //Обновление юзера
    @Override
    @SneakyThrows
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto)  {
        Integer parseId = Integer.parseInt(id);
        return mapper.employeeToEmployeeReadDto(
                service.updateById(parseId, mapper.employeeDtoToEmployee(employeeDto)
                )
        );
    }

    //Удаление по id
    @Override
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable String id) {
        Integer parseId = Integer.parseInt(id);
        service.removeById(parseId);
    }

    //Удаление всех юзеров
    @Override
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    @Override
    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        service.sendEmailByCountry(country, text);
    }

    @Override
    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String cities, @RequestBody String text) {
        service.sendEmailByCity(cities, text);
    }

    @Override
    @PostMapping("/fillingDataBase/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void fillingDataBase(@PathVariable String quantity) {
        service.fillingDataBase(quantity);
    }

    @Override
    @PostMapping("/updateBaseByCountryFully")
    @ResponseStatus(HttpStatus.OK)
    public void updateByCountryFully(@RequestParam String countries) {
        service.updaterByCountryFully(countries);
    }

    @Override
    @PostMapping("/updateBaseByCountrySmart")
    @ResponseStatus(HttpStatus.OK)
    public void updateByCountrySmart(@RequestParam String countries) {
        service.updaterByCountrySmart(countries);
    }

    @Override
    @PostMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        service.processor();
    }

    @Override
    @PostMapping("/sendEmailPhoto")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> sendEmailPhoto() {
        List<Employee> emplList=service.sendMailToEverybody();
        List<EmployeeReadDto> employeeReadDtoList=new ArrayList<>();
        for (Employee empl:emplList){
            employeeReadDtoList.add(mapper.employeeToEmployeeReadDto(empl));
        }
        return employeeReadDtoList;
    }

    @Override
    @PostMapping("/sendEmailToCoutriesSwappers")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> sendMailToCountrySwappedEmployees(){
        List<Employee> emplList=service.sendMailToCountrySwappers();
        List<EmployeeReadDto> employeeReadDtoList=new ArrayList<>();
        for (Employee empl:emplList){
            employeeReadDtoList.add(mapper.employeeToEmployeeReadDto(empl));
        }
        return employeeReadDtoList;
    }
}