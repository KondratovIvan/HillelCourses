package com.example.demowithtests.util.config.mapper;

import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-30T21:14:57+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.3 (JetBrains s.r.o.)"
)
@Component
public class PassportMapperImpl implements PassportMapper {

    @Override
    public Passport fromRequestDto(PassportRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Passport.PassportBuilder passport = Passport.builder();

        passport.firstName( requestDto.firstName );
        passport.secondName( requestDto.secondName );
        passport.birthDate( requestDto.birthDate );

        return passport.build();
    }

    @Override
    public PassportRequestDto toRequestDto(Passport passport) {
        if ( passport == null ) {
            return null;
        }

        PassportRequestDto passportRequestDto = new PassportRequestDto();

        passportRequestDto.firstName = passport.getFirstName();
        passportRequestDto.secondName = passport.getSecondName();
        passportRequestDto.birthDate = passport.getBirthDate();

        return passportRequestDto;
    }

    @Override
    public PassportResponseDto toResponseDto(Passport passport) {
        if ( passport == null ) {
            return null;
        }

        PassportResponseDto passportResponseDto = new PassportResponseDto();

        passportResponseDto.setSerialNumber( passport.getSerialNumber() );
        passportResponseDto.setFirstName( passport.getFirstName() );
        passportResponseDto.setSecondName( passport.getSecondName() );
        passportResponseDto.setBirthDate( passport.getBirthDate() );

        return passportResponseDto;
    }

    @Override
    public Passport fromResponseDto(PassportResponseDto responseDto) {
        if ( responseDto == null ) {
            return null;
        }

        Passport.PassportBuilder passport = Passport.builder();

        passport.firstName( responseDto.getFirstName() );
        passport.secondName( responseDto.getSecondName() );
        passport.birthDate( responseDto.getBirthDate() );

        return passport.build();
    }
}
