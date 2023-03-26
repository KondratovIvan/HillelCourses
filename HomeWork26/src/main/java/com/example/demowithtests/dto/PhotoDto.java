package com.example.demowithtests.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class PhotoDto {

    public Integer id;
    public String linkPhoto;
    public Integer height;
    public Integer width;
    public LocalDate createDate= LocalDate.now();

    public Boolean isVisible = Boolean.TRUE;
}
