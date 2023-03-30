package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String linkPhoto;
    private Integer height;
    private Integer width;
    private LocalDate createDate= LocalDate.now();

    private Boolean isVisible = Boolean.TRUE;

}
