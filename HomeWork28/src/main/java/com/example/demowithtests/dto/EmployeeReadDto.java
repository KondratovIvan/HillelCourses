package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Address;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

public class EmployeeReadDto {
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;
    //public String country;
    @Schema(description = "Email of an employee.", example = "test@gmail.com", required = true)
    public String email;

    @Schema(description = "Photos of an employee.", example = "photo1, photo2", required = true)
    public Set<PhotoDto> photos = new HashSet<>();

    @Schema(description = "Photos of an employee.", example = "photo1, photo2", required = true)
    public Set<Address> addresses = new HashSet<>();
}
