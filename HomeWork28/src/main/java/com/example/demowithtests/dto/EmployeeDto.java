package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Address;
import com.example.demowithtests.domain.Photo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class EmployeeDto {
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;
    @Schema(description = "Country of an employee.", example = "Ukraine", required = true)
    public String country;
    @Schema(description = "Email of an employee.", example = "test@gmail.com", required = true)
    public String email;
    //public Date creationTime = Date.from(Instant.now());

    @Schema(description = "Photos of an employee.", example = "photo1, photo2", required = true)
    public Set<PhotoDto> photos = new HashSet<>();
    @Schema(description = "Addresses of an employee.", example = "address1,address2", required = true)
    public Set<Address> addresses = new HashSet<>();


}
