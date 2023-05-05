package com.example.demowithtests.dto.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;


@ToString
public class RegistrationDto {
    @Schema(description = "City in a registration.", example = "Kyiv", required = true)
    private String city;
    @Schema(description = "Street in a registration.", example = "Khreshchatik", required = true)
    private String street;
    @Schema(description = "houseNumber in a registration.", example = "1", required = true)
    private Integer houseNumber;
    @Schema(description = "flatNumber in a registration.", example = "50", required = true)
    private Integer flatNumber;

}