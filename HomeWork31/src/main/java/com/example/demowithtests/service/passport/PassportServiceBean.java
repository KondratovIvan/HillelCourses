package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.IncorrectDataEnterException;
import com.example.demowithtests.util.ListHasNoAnyElementsException;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.UnrealAgeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PassportServiceBean implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport create(Passport passport) {
        log.debug("PassportService --> create() - start: passport = {}",passport);
        if(passport.getFirstName()==null||passport.getSecondName()==null||passport.getBirthDate()==null){
            throw new IncorrectDataEnterException();
        }
        if(passport.getBirthDate().isAfter(LocalDate.of(1859, 1, 1))&&passport.getBirthDate().isBefore(LocalDate.of(2009, 04, 03))){
            Passport createdPassport=passportRepository.save(passport);
            log.debug("PassportService --> create() - end: passport = {}",createdPassport);
            return createdPassport;
        }
       else {
           throw new UnrealAgeException();
        }
    }

    @Override
    public List<Passport> getAll() {
        log.debug("PassportService --> getAll() - start");
        List<Passport> receivedPassports=passportRepository.findAll();
        if(receivedPassports.isEmpty()){
            throw new ListHasNoAnyElementsException();
        }
        log.debug("PassportService --> getAll() - end: passports = {}",receivedPassports);
        return receivedPassports;
    }

    @Override
    public Passport getById(Integer id) {
        log.debug("PassportService --> getById() - start: id = {}",id);
        Passport receivedPassport=passportRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        log.debug("PassportService --> getById() - end: passport = {}",receivedPassport);
        return receivedPassport;
    }

    @Override
    public Passport updateById(Integer id, Passport passport) {
        log.debug("PassportService --> updateById() - start: id = {}, passport = {}",id,passport);
        Passport entity = passportRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        entity.setFirstName(passport.getFirstName());
        entity.setSecondName(passport.getSecondName());
        Passport updatePassport = passportRepository.save(entity);
        log.debug("PassportService --> updateById() - end: passport = {}",updatePassport);
        return updatePassport;
    }

    @Override
    public Passport getPassportByRegistration(Integer id) {
        log.debug("PassportService --> getPassportByRegistration() - start: id = {}",id);
        Passport receivedPassport=passportRepository.findPassportByRegistrations(id);
        log.debug("PassportService --> updateById() - end: passport = {}",receivedPassport);
        return receivedPassport;
    }

}