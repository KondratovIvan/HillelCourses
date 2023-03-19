package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = " select e from Employee e where e.country=:country")
    List<Employee> findEmployeeByCountry(String country);

    @Query(value = "select e from Employee e join Address a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

//    @Query(value = "select e from Employee e join e.addresses a where a.street=:street")
//    List<Employee> findEmployeeByStreet(String street);

    @Query(value = "select * from users join addresses on users.id=addresses.employee_id where addresses.street='1' ",nativeQuery = true)
    List<Employee> findEmployeeByStreet(String street);

    @Query(value = "select * from users where id between 0 and ?",nativeQuery = true)
    List<Employee> findEmployeeById(Integer id);

    @Query(value = "select * from users where id between ?1 and ?2",nativeQuery = true)
    List<Employee> findEmployeeByIdUpd(Integer startId, Integer finishId);
}