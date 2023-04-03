package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.util.config.EmployeeMapper;
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

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeControllerBean(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }


    //Операция сохранения юзера в базу данных
    @Override
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("EmployeeController --> saveEmployee() - start: employeeDto = {}", employeeDto);
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = employeeMapper.employeeToEmployeeDto(employeeService.create(employee));
        log.info("EmployeeController --> saveEmployee() - end: employeeDto = {}", dto);
        return dto;
    }

    //Получение списка юзеров
    @Override
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsers() {
        log.info("EmployeeController --> getAllUsers() - start");
        List<Employee> employees = employeeService.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(
                    employeeMapper.employeeToEmployeeReadDto(employee)
            );
        }
        log.info("EmployeeController --> getAllUsers() - end: employeesReadDto = {}", employeesReadDto);
        return employeesReadDto;
    }

    //Получения юзера по id
    @Override
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable String id) {
        log.info("EmployeeController --> getEmployeeById() - start: id = {}", id);
        EmployeeReadDto empl= employeeMapper.employeeToEmployeeReadDto(
                employeeService.getById(id));
        log.info("EmployeeController --> getEmployeeById() - end: EmployeeReadDto = {}", empl);
        return empl;

    }

    //Обновление юзера
    @Override
    @SneakyThrows
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto)  {
        log.info("EmployeeController --> refreshEmployee() - start: id = {}, employeeDto = {}:", id,employeeDto);
        Integer parseId = Integer.parseInt(id);
        EmployeeReadDto refreshedEmployee= employeeMapper.employeeToEmployeeReadDto(
                employeeService.updateById(parseId, employeeMapper.employeeDtoToEmployee(employeeDto)
                )
        );
        log.info("EmployeeController --> refreshEmployee() - end: EmployeeReadDto = {}", refreshedEmployee);
        return refreshedEmployee;
    }

    //Удаление по id
    @Override
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable String id) {
        log.info("EmployeeController --> removeEmployeeById() - start: id = {}", id);
        Integer parseId = Integer.parseInt(id);
        employeeService.removeById(parseId);
        log.info("EmployeeController --> removeEmployeeById() - end");
    }

    //Удаление всех юзеров
    @Override
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        log.info("EmployeeController --> removeAllUsers() - start");
        employeeService.removeAll();
        log.info("EmployeeController --> removeAllUsers() - end");
    }

    @Override
    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        log.info("EmployeeController --> sendEmailByCountry() - start: country = {}, text = {}", country,text);
        employeeService.sendEmailByCountry(country, text);
        log.info("EmployeeController --> sendEmailByCountry() - end");
    }

    @Override
    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String cities, @RequestBody String text) {
        log.info("EmployeeController --> sendEmailByCity() - start: cities = {}, text = {}", cities,text);
        employeeService.sendEmailByCity(cities, text);
        log.info("EmployeeController --> sendEmailByCity() - end");
    }

    @Override
    @PostMapping("/fillingDataBase/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void fillingDataBase(@PathVariable String quantity) {
        log.info("EmployeeController --> fillingDataBase() - start: quantity = {}", quantity);
        employeeService.fillingDataBase(quantity);
        log.info("EmployeeController --> fillingDataBase() - end:");
    }

    @Override
    @PostMapping("/updateBaseByCountryFully")
    @ResponseStatus(HttpStatus.OK)
    public void updateByCountryFully(@RequestParam String countries) {
        log.info("EmployeeController --> updateByCountryFully() - start: countries = {}", countries);
        employeeService.updaterByCountryFully(countries);
        log.info("EmployeeController --> updateByCountryFully() - end:");
    }

    @Override
    @PostMapping("/updateBaseByCountrySmart")
    @ResponseStatus(HttpStatus.OK)
    public void updateByCountrySmart(@RequestParam String countries) {
        log.info("EmployeeController --> updateByCountrySmart() - start: countries = {}", countries);
        employeeService.updaterByCountrySmart(countries);
        log.info("EmployeeController --> updateByCountryFully() - end:");
    }

    @Override
    @PostMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        log.info("EmployeeController --> replaceNull() - start");
        employeeService.processor();
        log.info("EmployeeController --> replaceNull() - end");
    }

    @Override
    @PostMapping("/sendEmailPhoto")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> sendEmailPhoto() {
        log.info("EmployeeController --> sendEmailPhoto() - start");
        List<Employee> emplList= employeeService.sendMailToEverybody();
        List<EmployeeReadDto> employeeReadDtoList=new ArrayList<>();
        for (Employee empl:emplList){
            employeeReadDtoList.add(employeeMapper.employeeToEmployeeReadDto(empl));
        }
        log.info("EmployeeController --> sendEmailPhoto() - end: List<EmployeeReadDto> = {}",employeeReadDtoList);
        return employeeReadDtoList;
    }

    @Override
    @PostMapping("/sendEmailToCoutriesSwappers")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> sendMailToCountrySwappedEmployees(){
        log.info("EmployeeController --> sendMailToCountrySwappedEmployees() - start");
        List<Employee> emplList= employeeService.sendMailToCountrySwappers();
        List<EmployeeReadDto> employeeReadDtoList=new ArrayList<>();
        for (Employee empl:emplList){
            employeeReadDtoList.add(employeeMapper.employeeToEmployeeReadDto(empl));
        }
        log.info("EmployeeController --> sendMailToCountrySwappedEmployees() - end: List<EmployeeReadDto> = {}",employeeReadDtoList);
        return employeeReadDtoList;
    }
}