package com.example.demowithtests;


import com.example.demowithtests.domain.employee.Address;
import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Employee Repository Tests")
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Save employee test")
    public void saveEmployeeTest() {

        var employee = Employee.builder()
                .name("Mark")
                .country("England")
                .addresses(new HashSet<>(Set.of(
                        Address
                                .builder()
                                .country("UK")
                                .build())))
                .passport(new Passport())
                .build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
        Assertions.assertThat(employee.getId()).isEqualTo(1);
        Assertions.assertThat(employee.getName()).isEqualTo("Mark");
    }

    @Test
    @Order(2)
    @DisplayName("Get employee by id test")
    public void getEmployeeTest() {

        var employee = employeeRepository.findById(1).orElseThrow();

        Assertions.assertThat(employee.getId()).isEqualTo(1);
        Assertions.assertThat(employee.getName()).isEqualTo("Mark");
    }

    @Test
    @Order(3)
    @DisplayName("Get employees test")
    public void getListOfEmployeeTest() {

        var employeesList = employeeRepository.findAll();

        Assertions.assertThat(employeesList.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    @DisplayName("Update employee test")
    public void updateEmployeeTest() {

        var employee = employeeRepository.findById(1).orElseThrow();

        employee.setName("Martin");
        var employeeUpdated = employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    @DisplayName("Delete employee test")
    public void deleteEmployeeTest() {

        var employee = employeeRepository.findById(1).orElseThrow();

        employeeRepository.delete(employee);

        Employee employeeNull = null;

        var optionalEmployee = Optional.ofNullable(employeeRepository.findByName("Martin"));

        if (optionalEmployee.isPresent()) {
            employeeNull = optionalEmployee.orElseThrow();
        }

        Assertions.assertThat(employeeNull).isNull();
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    @DisplayName("Delete all employees test")
    public void deleteAllEmployeesTest() {
        Employee employee1 = new Employee("TestEmpl","Ukraine","testmail@gmail.com");
        Employee employee2 = new Employee("TestEmpl","Ukraine","testmail@gmail.com");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> firstEmplList=employeeRepository.findAll();

        employeeRepository.deleteAll();

        List<Employee> secondEmplList=employeeRepository.findAll();

        Assertions.assertThat(firstEmplList.size()).isGreaterThan(0);
        Assertions.assertThat(secondEmplList).isEmpty();
    }
}
