package com.example.demowithtests.dto.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@ToString
@Getter
@Setter
public class PassportResponseDto {
    @Schema(description = "SerialNumber in a passport.", example = "9e6a6c17-db8e-4a40-a809-354f34643f7a", required = true)
    public final UUID serialNumber = UUID.randomUUID();
    @Schema(description = "FirstName in a passport.", example = "Billy", required = true)
    public String firstName;
    @Schema(description = "SecondName in a passport.", example = "Herrington", required = true)
    public String secondName;
    @Schema(description = "BirthDate in a passport.", example = "2014-01-01", required = true)
    public LocalDate birthDate;

}
