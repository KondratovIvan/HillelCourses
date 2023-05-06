package com.example.demowithtests.repository;

import com.example.demowithtests.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
//@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    Employee findEmployeeByEmail(String email);

    //Boolean existsAny();

    List<Employee> findEmployeeByIsDeletedIsTrue();


    //List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = " select e from Employee e where e.country=:country")
    List<Employee> findEmployeeByCountry(String country);

    @Query(value = "select e from Employee e join e.addresses a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

    static void saveAllSmart(List<Employee> employees) {};
    List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = "select * from users join photos on users.id=photos.employee_id  where photos.create_date<'2019-01-01'",nativeQuery = true)
    List<Employee> findEmployeeByLatePhoto();

    @Query(value = "select * from users join addresses on users.id=addresses.employee_id where addresses.country='Ukraine' and addresses.address_has_active=false", nativeQuery = true)
    List<Employee> findEmployeeWhoChangedCountry();
}