package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    List<Employee> processor();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    List<Employee> sendEmailByStreet(String street, String text);

    void databaseFiller(Integer amount);

    void databaseUpdater(Integer amount, String country);
    void databaseSQLUpdater(Integer amount, String country);
    void databaseSQLUpdaterUpd(Integer startId,Integer finishId, String country);
}
