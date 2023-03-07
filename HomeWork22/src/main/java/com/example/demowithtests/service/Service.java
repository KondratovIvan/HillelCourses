package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Optional<Employee> updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();


}
