package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class PhotoDto {
    @Schema(description = "Id of an employee`s photo.", example = "1", required = true)
    public Integer id;
    @Schema(description = "Link of an employee`s photo.", example = "url", required = true)
    public String linkPhoto;
    @Schema(description = "Height of an employee`s photo.", example = "10", required = true)
    public Integer height;
    @Schema(description = "Width of an employee`s photo.", example = "20", required = true)
    public Integer width;
    @Schema(description = "Creation date of an employee`s photo.", example = "01.01.2004", required = true)
    public LocalDate createDate= LocalDate.now();
    @Schema(description = "Visibility of an employee`s photo.", example = "true", required = true)
    public Boolean isVisible = Boolean.TRUE;
}
