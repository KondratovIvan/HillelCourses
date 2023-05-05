package com.example.demowithtests.web.passport;

import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PassportController {
    @Operation(summary = "This is endpoint to add a new passport.", description = "Create request to add a new passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Passport already exists")})
    PassportResponseDto savePassport(@RequestBody PassportRequestDto requestDto);
    @Operation(summary = "This is endpoint to get all  passports.", description = "Create request to get all passports.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passports successfully got"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found.")})
    List<PassportResponseDto> getAllPassports();
    @Operation(summary = "This is endpoint to get passport by id.", description = "Create request to get passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passport successfully got"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found.")})
    PassportResponseDto getPassportById(@PathVariable Integer id);
    @Operation(summary = "This is endpoint to update passport by id.", description = "Create request to update passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passport successfully updated"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found.")})
    PassportResponseDto refreshPassport(@PathVariable("id") Integer id, @RequestBody PassportRequestDto requestDto);
    @Operation(summary = "This is endpoint to get passport by registration id.", description = "Create request to get passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passport successfully got"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found.")})
    PassportResponseDto getPassportByRegistration(@PathVariable Integer id);
}