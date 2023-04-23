package com.example.demowithtests.util.config.mapper;

import com.example.demowithtests.domain.employee.Address;
import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.employee.Employee.EmployeeBuilder;
import com.example.demowithtests.domain.employee.Photo;
import com.example.demowithtests.domain.employee.Photo.PhotoBuilder;
import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.domain.passport.Passport.PassportBuilder;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.employee.PhotoDto;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-23T21:42:54+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (JetBrains s.r.o.)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.name( employeeDto.name );
        employee.country( employeeDto.country );
        employee.email( employeeDto.email );
        Set<Address> set = employeeDto.addresses;
        if ( set != null ) {
            employee.addresses( new HashSet<Address>( set ) );
        }
        employee.photos( photoDtoSetToPhotoSet( employeeDto.photos ) );
        employee.passport( passportRequestDtoToPassport( employeeDto.passport ) );

        return employee.build();
    }

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.name = employee.getName();
        employeeDto.country = employee.getCountry();
        employeeDto.email = employee.getEmail();
        employeeDto.photos = photoSetToPhotoDtoSet( employee.getPhotos() );
        Set<Address> set1 = employee.getAddresses();
        if ( set1 != null ) {
            employeeDto.addresses = new HashSet<Address>( set1 );
        }
        employeeDto.passport = passportToPassportRequestDto( employee.getPassport() );

        return employeeDto;
    }

    @Override
    public EmployeeReadDto employeeToEmployeeReadDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeReadDto employeeReadDto = new EmployeeReadDto();

        employeeReadDto.name = employee.getName();
        employeeReadDto.email = employee.getEmail();
        employeeReadDto.photos = photoSetToPhotoDtoSet( employee.getPhotos() );
        Set<Address> set1 = employee.getAddresses();
        if ( set1 != null ) {
            employeeReadDto.addresses = new HashSet<Address>( set1 );
        }
        employeeReadDto.passport = passportToPassportResponseDto( employee.getPassport() );

        return employeeReadDto;
    }

    @Override
    public Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto) {
        if ( employeeReadDto == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.name( employeeReadDto.name );
        employee.email( employeeReadDto.email );
        Set<Address> set = employeeReadDto.addresses;
        if ( set != null ) {
            employee.addresses( new HashSet<Address>( set ) );
        }
        employee.photos( photoDtoSetToPhotoSet( employeeReadDto.photos ) );
        employee.passport( passportResponseDtoToPassport( employeeReadDto.passport ) );

        return employee.build();
    }

    protected Photo photoDtoToPhoto(PhotoDto photoDto) {
        if ( photoDto == null ) {
            return null;
        }

        PhotoBuilder photo = Photo.builder();

        photo.id( photoDto.id );
        photo.linkPhoto( photoDto.linkPhoto );
        photo.height( photoDto.height );
        photo.width( photoDto.width );
        photo.createDate( photoDto.createDate );
        photo.isVisible( photoDto.isVisible );

        return photo.build();
    }

    protected Set<Photo> photoDtoSetToPhotoSet(Set<PhotoDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Photo> set1 = new HashSet<Photo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PhotoDto photoDto : set ) {
            set1.add( photoDtoToPhoto( photoDto ) );
        }

        return set1;
    }

    protected Passport passportRequestDtoToPassport(PassportRequestDto passportRequestDto) {
        if ( passportRequestDto == null ) {
            return null;
        }

        PassportBuilder passport = Passport.builder();

        passport.firstName( passportRequestDto.firstName );
        passport.secondName( passportRequestDto.secondName );
        passport.birthDate( passportRequestDto.birthDate );

        return passport.build();
    }

    protected PhotoDto photoToPhotoDto(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoDto photoDto = new PhotoDto();

        photoDto.id = photo.getId();
        photoDto.linkPhoto = photo.getLinkPhoto();
        photoDto.height = photo.getHeight();
        photoDto.width = photo.getWidth();
        photoDto.createDate = photo.getCreateDate();
        photoDto.isVisible = photo.getIsVisible();

        return photoDto;
    }

    protected Set<PhotoDto> photoSetToPhotoDtoSet(Set<Photo> set) {
        if ( set == null ) {
            return null;
        }

        Set<PhotoDto> set1 = new HashSet<PhotoDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Photo photo : set ) {
            set1.add( photoToPhotoDto( photo ) );
        }

        return set1;
    }

    protected PassportRequestDto passportToPassportRequestDto(Passport passport) {
        if ( passport == null ) {
            return null;
        }

        PassportRequestDto passportRequestDto = new PassportRequestDto();

        passportRequestDto.firstName = passport.getFirstName();
        passportRequestDto.secondName = passport.getSecondName();
        passportRequestDto.birthDate = passport.getBirthDate();

        return passportRequestDto;
    }

    protected PassportResponseDto passportToPassportResponseDto(Passport passport) {
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

    protected Passport passportResponseDtoToPassport(PassportResponseDto passportResponseDto) {
        if ( passportResponseDto == null ) {
            return null;
        }

        PassportBuilder passport = Passport.builder();

        passport.firstName( passportResponseDto.getFirstName() );
        passport.secondName( passportResponseDto.getSecondName() );
        passport.birthDate( passportResponseDto.getBirthDate() );

        return passport.build();
    }
}
