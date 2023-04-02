package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EmployeeController {
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto);

    @Operation(summary = "This is endpoint to get all  employees.", description = "Create request to get all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees successfully got"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    List<EmployeeReadDto> getAllUsers();

    @Operation(summary = "This is endpoint to get employee by id.", description = "Create request to get employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully got"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    EmployeeReadDto getEmployeeById(@PathVariable String id);

    @Operation(summary = "This is endpoint to update employee by id.", description = "Create request to update employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully updated"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto);

    @Operation(summary = "This is endpoint to remove employee by id.", description = "Create request to remove employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully removed"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    void removeEmployeeById(@PathVariable String id);

    @Operation(summary = "This is endpoint to remove all employees.", description = "Create request to remove all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully updated"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    void removeAllUsers();

    @Operation(summary = "This is endpoint to send email by country.", description = "Create request to send email.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Emails successfully sent"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified country request not found."),
            @ApiResponse(responseCode = "409", description = "Email already exists")})
    void sendEmailByCountry(@RequestParam String country, @RequestParam String text);

    @Operation(summary = "This is endpoint to send email by city.", description = "Create request to send email.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Emails successfully sent"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified city request not found."),
            @ApiResponse(responseCode = "409", description = "Email already exists")})
    void sendEmailByCity(@RequestParam String cities, @RequestBody String text);

    @Operation(summary = "This is endpoint to send email by city.", description = "Create request to send email.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. database was filled.")})
    void fillingDataBase(@PathVariable String quantity);

    @Operation(summary = "This is endpoint to update employee by country.", description = "Create request to update employee`s country.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    void updateByCountryFully(@RequestParam String countries);

    @Operation(summary = "This is endpoint to update employee by country.", description = "Create request to update employee`s country.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    void updateByCountrySmart(@RequestParam String countries);

    @Operation(summary = "This is endpoint to set all employee`s IsDeleted null.", description = "Create request to delete all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully updated")})
    void replaceNull();

    @Operation(summary = "This is endpoint to send mail to update photo.", description = "Create request to send email.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Emails successfully sent"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified city request not found."),
            @ApiResponse(responseCode = "409", description = "Email already exists")})
    List<EmployeeReadDto> sendEmailPhoto();

    @Operation(summary = "This is endpoint to send mail to employees who swapped countries.", description = "Create request to send email.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Emails successfully sent"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Email already exists")})
    List<EmployeeReadDto> sendMailToCountrySwappedEmployees();
}
