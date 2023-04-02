package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Address;
import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.PhotoDto;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-02T16:00:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3 (JetBrains s.r.o.)"
)
@Component
public class MapperImpl implements Mapper {

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.name( employeeDto.name );
        employee.country( employeeDto.country );
        employee.email( employeeDto.email );
        Set<Address> set = employeeDto.addresses;
        if ( set != null ) {
            employee.addresses( new LinkedHashSet<Address>( set ) );
        }
        employee.photos( photoDtoSetToPhotoSet( employeeDto.photos ) );

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
            employeeDto.addresses = new LinkedHashSet<Address>( set1 );
        }

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
            employeeReadDto.addresses = new LinkedHashSet<Address>( set1 );
        }

        return employeeReadDto;
    }

    @Override
    public Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto) {
        if ( employeeReadDto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.name( employeeReadDto.name );
        employee.email( employeeReadDto.email );
        Set<Address> set = employeeReadDto.addresses;
        if ( set != null ) {
            employee.addresses( new LinkedHashSet<Address>( set ) );
        }
        employee.photos( photoDtoSetToPhotoSet( employeeReadDto.photos ) );

        return employee.build();
    }

    protected Photo photoDtoToPhoto(PhotoDto photoDto) {
        if ( photoDto == null ) {
            return null;
        }

        Photo.PhotoBuilder photo = Photo.builder();

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

        Set<Photo> set1 = new LinkedHashSet<Photo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PhotoDto photoDto : set ) {
            set1.add( photoDtoToPhoto( photoDto ) );
        }

        return set1;
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

        Set<PhotoDto> set1 = new LinkedHashSet<PhotoDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Photo photo : set ) {
            set1.add( photoToPhotoDto( photo ) );
        }

        return set1;
    }
}
