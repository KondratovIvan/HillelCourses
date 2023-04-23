package com.example.demowithtests.web.workplace;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import com.example.demowithtests.service.workplace.WorkplaceService;
import com.example.demowithtests.util.config.mapper.WorkplaceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Workplace", description = "Workplace API")
public class WorkplaceControllerBean implements WorkplaceController {

    private final WorkplaceService workplaceService;
    private final WorkplaceMapper workplaceMapper;
    @Override
    @PostMapping("/workplace")
    public WorkplaceResponseDto saveWorkplace(WorkplaceRequestDto workplaceRequestDto) {
        log.info("WorkplaceController --> saveWorkplace() - start: workplaceRequestDto = {}",workplaceRequestDto);
        Workplace workplace=workplaceService.create(workplaceMapper.workplaceRequestDtoToWorkplace(workplaceRequestDto));
        WorkplaceResponseDto savedWorkplace=workplaceMapper.workplaceToResponseDto(workplace);
        log.info("WorkplaceController --> saveWorkplace() - end: WorkplaceResponseDto = {}",savedWorkplace);
        return savedWorkplace;
    }

    @Override
    @GetMapping(value = "/workplace/{id}")
    public WorkplaceResponseDto getWorkplaceById(Integer id) {
        log.info("WorkplaceController --> getWorkplaceById() - start: id = {}",id);
        WorkplaceResponseDto workplaceResponseDto=workplaceMapper.workplaceToResponseDto(workplaceService.getById(id));
        log.info("WorkplaceController --> getWorkplaceById() - end: WorkplaceResponseDto = {}",workplaceResponseDto);
        return workplaceResponseDto;
    }

//    @Override
//    @GetMapping(value = "/workplaceCheck/{id}")
//    public Boolean isWorkplaceFull(Integer id) {
//        log.info("WorkplaceController --> isWorkplaceFull() - start: id = {}",id);
//        Boolean result=workplaceService.isWorkplaceFull(id-1);
//        log.info("WorkplaceController --> isWorkplaceFull() - end: Boolean = {}",result);
//        return result;
//    }

    @Override
    @GetMapping(value="/workplaceOffer/{id}")
    public Workplace newWorkplaceOffer(Integer id){
        log.info("WorkplaceController --> newWorkplaceOffer() - start: id = {}",id);
        Workplace offer=workplaceService.newWorkplaceOffer(id-1);
        log.info("WorkplaceController --> newWorkplaceOffer() - end: Workplace = {}",offer);
        return offer;
    };
}
