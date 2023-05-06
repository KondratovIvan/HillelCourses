package com.example.demowithtests.web.passport;

import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.util.config.mapper.PassportMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassportControllerBean implements PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;

    @Override
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    public PassportResponseDto savePassport(PassportRequestDto requestDto) {
        log.info("PassportController --> savePassport() - start: requestDto = {}",requestDto);
        Passport passport = passportMapper.fromRequestDto(requestDto);
        PassportResponseDto savedPassport=passportMapper.toResponseDto(passportService.create(passport));
        log.info("PassportController --> savePassport() - end: savedPassport = {}",savedPassport);
        return savedPassport;
    }

    @Override
    @GetMapping("/passports")
    @ResponseStatus(HttpStatus.OK)
    public List<PassportResponseDto> getAllPassports() {
        log.info("PassportController --> savePassport() - start");
        List<Passport> passports = passportService.getAll();
        List<PassportResponseDto> responseDtos = new ArrayList<>();
        for (Passport passport : passports) {
            responseDtos.add(passportMapper.toResponseDto(passport));
        }
        log.info("PassportController --> savePassport() - end: responseDtos = {}",responseDtos);
        return responseDtos;
    }

    @Override
    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto getPassportById(Integer id) {
        log.info("PassportController --> savePassport() - start: id = {}",id);
        Passport passport = passportService.getById(id);
        PassportResponseDto receivedPassport=passportMapper.toResponseDto(passport);
        log.info("PassportController --> savePassport() - end: receivedPassport = {}",receivedPassport);
        return receivedPassport;
    }

    @Override
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto refreshPassport(Integer id, PassportRequestDto requestDto) {
        log.info("PassportController --> savePassport() - start: id = {}, requestDto = {}",id,requestDto);
        PassportResponseDto responseDto = passportMapper
                .toResponseDto(passportService.updateById(id, passportMapper.fromRequestDto(requestDto)));
        log.info("PassportController --> savePassport() - end: responseDto = {}",responseDto);
        return responseDto;
    }

    @Override
    @GetMapping("/registrations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto getPassportByRegistration(@PathVariable Integer id) {
        log.info("PassportController --> getPassportByRegistration() - start: id = {}",id);
        Passport receivedPassport=passportService.getPassportByRegistration(id);
        PassportResponseDto passportResponseDto=passportMapper.toResponseDto(receivedPassport);
        log.info("PassportController --> getPassportByRegistration() - end: passportResponseDto = {}",passportResponseDto);
        return passportResponseDto;
    }
}