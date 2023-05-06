package com.example.demowithtests.web.workplace;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface WorkplaceController {
    @Operation(summary = "This is endpoint to add a new workplace.", description = "Create request to add a new workplace.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new workplace is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found."),
            @ApiResponse(responseCode = "409", description = "Workplace already exists")})
    @ResponseStatus(HttpStatus.CREATED)
    WorkplaceResponseDto saveWorkplace(@RequestBody WorkplaceRequestDto workplaceRequestDto);

    @Operation(summary = "This is endpoint to get workplace by id.", description = "Create request to get workplace.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workplace successfully got"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found.")})
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto getWorkplaceById(@PathVariable Integer id);

//    @Operation(summary = "This is endpoint to check if workplace is full of employees.", description = "Create request to check if workplace is full of employees.", tags = {"Workplace"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Workplace successfully checked"),
//            @ApiResponse(responseCode = "400", description = "Invalid input"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found.")})
//    Boolean isWorkplaceFull(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to offer user if workplace is full of employees to change it .", description = "Create request to get offer if workplace is full of employees to change it.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workplace successfully offered"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found.")})
    Workplace newWorkplaceOffer(@PathVariable Integer id);

}
