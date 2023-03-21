package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.WrongTypeOfDataException;
import com.example.demowithtests.util.mapStruct.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;

//    public EmployeeReadDto dtoMaker(@RequestBody EmployeeDto employeeDto, String method,@PathVariable String id){
//        var entity = employeeConverter.getMapperFacade().map(employeeDto, Employee.class);
//        var dto = null;
//        switch (method) {
//            case "create":
//                dto = employeeConverter.toReadDto(service.create(entity));
//                break;
//            case "getAll":
//                service.getAll();
//                break;
//            case "update":
//                Integer parsedId = Integer.parseInt(id);
//                dto = employeeConverter.toReadDto(service.getById(parsedId));
//                break;
//
//            default:
//                throw new IllegalArgumentException("Invalid method: " + method);
//        }
//        return dto;
//    }

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeReadDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("+++ with dto Start +++");
        var entity = EmployeeMapper.INSTANCE.toEmployee(employeeDto);
        var dto = EmployeeMapper.INSTANCE.toReadDTO(entity);
        log.info("+++ with dto Finish +++");
        return dto;
        //service.create(employee);
    }




    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsers() {
        log.info("+++ with dto Start +++");
        List<Employee> emplList=service.getAll();
        List<EmployeeReadDto> emplDtoList=new ArrayList<>();
        for (Employee employee: emplList) {
            emplDtoList.add(EmployeeMapper.INSTANCE.toReadDTO(employee));
        }
        log.info("+++ with dto Finish +++");
        return emplDtoList;
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable String id) throws WrongTypeOfDataException {
        Integer parsedId = Integer.parseInt(id);
        log.info("+++ with dto Start +++");
        var dto = EmployeeMapper.INSTANCE.toReadDTO(service.getById(parsedId));
        log.info("+++ with dto Finish +++");
        return dto;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto) throws WrongTypeOfDataException{
        Integer parseId=Integer.parseInt(id);
        log.info("+++ with dto Start +++");
        var entity = EmployeeMapper.INSTANCE.toEmployee(employeeDto);
        var dto = EmployeeMapper.INSTANCE.toReadDTO(entity);
        log.info("+++ with dto Finish +++");
        return dto;
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable String id) {
        Integer parseId=Integer.parseInt(id);
        service.removeById(parseId);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }


    //@PatchMapping("/replaceNull")
    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull(){
        service.processor();
    }

    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text){
        service.sendEmailByCountry(country, text);
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text){
        service.sendEmailByCountry(city, text);
    }

    @PostMapping("/sendEmailByStreet")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByStreet(@RequestParam String street, @RequestParam String text){
        service.sendEmailByStreet(street, text);
    }

    @GetMapping("/databaseFiller")
    @ResponseStatus(HttpStatus.OK)
    public void databaseFiller(@RequestParam Integer amount){
        service.databaseFiller(amount);
    }

    @PutMapping("/databaseUpdater")
    @ResponseStatus(HttpStatus.OK)
    public void databaseUpdater(@RequestParam Integer amount, @RequestParam String country){
        service.databaseUpdater(amount,country);
    }

    @PutMapping("/databaseSQLUpdater")
    @ResponseStatus(HttpStatus.OK)
    public void databaseSQLUpdater(@RequestParam Integer amount, @RequestParam String country){
        service.databaseUpdater(amount,country);
    }

    @PutMapping("/databaseSQLUpdaterUpd")
    @ResponseStatus(HttpStatus.OK)
    public void databaseSQLUpdaterUpd(@RequestParam Integer startId,@RequestParam Integer finishId, @RequestParam String country){
        service.databaseSQLUpdaterUpd(startId,finishId,country);
    }
}
