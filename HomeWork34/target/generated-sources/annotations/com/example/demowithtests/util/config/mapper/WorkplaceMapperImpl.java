package com.example.demowithtests.util.config.mapper;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T16:15:54+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.3 (JetBrains s.r.o.)"
)
@Component
public class WorkplaceMapperImpl implements WorkplaceMapper {

    @Override
    public Workplace workplaceRequestDtoToWorkplace(WorkplaceRequestDto workplaceRequestDto) {
        if ( workplaceRequestDto == null ) {
            return null;
        }

        Workplace.WorkplaceBuilder workplace = Workplace.builder();

        workplace.name( workplaceRequestDto.name );
        workplace.address( workplaceRequestDto.address );
        Set<Employee> set = workplaceRequestDto.employees;
        if ( set != null ) {
            workplace.employees( new LinkedHashSet<Employee>( set ) );
        }

        return workplace.build();
    }

    @Override
    public WorkplaceResponseDto workplaceToResponseDto(Workplace workplace) {
        if ( workplace == null ) {
            return null;
        }

        WorkplaceResponseDto workplaceResponseDto = new WorkplaceResponseDto();

        workplaceResponseDto.name = workplace.getName();
        workplaceResponseDto.address = workplace.getAddress();
        Set<Employee> set = workplace.getEmployees();
        if ( set != null ) {
            workplaceResponseDto.employees = new LinkedHashSet<Employee>( set );
        }

        return workplaceResponseDto;
    }
}
