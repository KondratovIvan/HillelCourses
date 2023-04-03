package com.example.demowithtests.dto.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.LocalDate;

@ToString
public class PassportRequestDto {
    @Schema(description = "FirstName in a passport.", example = "Billy", required = true)
    public String firstName;
    @Schema(description = "SecondName in a passport.", example = "Herrington", required = true)
    public String secondName;
    @Schema(description = "BirthDate in a passport.", example = "2014-01-01", required = true)
    public LocalDate birthDate;

}