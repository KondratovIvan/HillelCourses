package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        if(employee.getName()==null||employee.getEmail()==null||employee.getCountry()==null){
            throw new EmptyUserParameterException();
        }
        else{
            if(repository.findEmployeeByEmail(employee.getEmail())==null){
                return repository.save(employee);
            }
            else {
                throw new ResourceAlreadyExistsException();
            }
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> allEmployees=repository.findAll();
        if(allEmployees.isEmpty()){
            throw new NothingToGetException();
        }
        else {
            if(allEmployees.size()>100){
                throw new TooMuchReturnedUsersException();
            }
            else {
                return repository.findAll();
            }
        }
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = repository.findById(id)
               // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceNotFoundException::new);
         /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/
        return employee;
    }

    @Override
    public Optional<Employee> updateById(Integer id, Employee employee) {
        if(repository.findEmployeeById(id)==null){
            throw new IdNotExistsException();
        }
        else {
            if(repository.findEmployeeById(id).getName().equals(employee.getName())&&repository.findEmployeeById(id).getEmail().equals(employee.getEmail())&&repository.findEmployeeById(id).getCountry().equals(employee.getCountry())){
                throw new SameUpdateException();
            }
            else {
                return repository.findById(id)
                        .map(entity -> {
                            entity.setName(employee.getName());
                            entity.setEmail(employee.getEmail());
                            entity.setCountry(employee.getCountry());
                            return repository.save(entity);
                        });
            }

        }

    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Employee employee = repository.findById(id)
               // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //employee.setIsDeleted(true);
        repository.delete(employee);
        //repository.save(employee);
    }

    @Override
    public void removeAll() {
        List<Employee> allEmployees=repository.findAll();
        if(allEmployees.isEmpty()){
            throw new NothingToRemoveException();
        }
        else {
            repository.deleteAll();
            List<Employee> allEmployeesAfterDel=repository.findAll();
            if(!allEmployeesAfterDel.isEmpty()){
                throw new NotAllWasDeletedException();
            }

        }


    }
}
