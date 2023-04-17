package com.example.demowithtests.dto.workplace;


import com.example.demowithtests.domain.employee.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@ToString
public class WorkplaceRequestDto {
    @NotNull
    @Size(min = 1, max=50, message = "Name must be longer that 0 symbols and shorter that 50 symbols")
    @Schema(description = "Name of an workplace.", example = "Tokyo")
    public String name;
    @Size(min = 1, max=50, message = "Address must be longer that 0 symbols and shorter that 50 symbols")
    @Schema(description = "Address of an workplace.", example = "address")
    public String address;
    @Schema(description = "Set of an employees who work in this workplace.", example = "employees")
    public Set<Employee> employees = new HashSet<>();
}