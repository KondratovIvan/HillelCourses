package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.WrongTypeOfDataException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable String id) throws WrongTypeOfDataException {

        Integer parsedId = Integer.parseInt(id);
        Employee employee = service.getById(parsedId);
        return employee;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") String id, @RequestBody Employee employee) throws WrongTypeOfDataException{
        Integer parseId=Integer.parseInt(id);
        return service.updateById(parseId, employee);
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
