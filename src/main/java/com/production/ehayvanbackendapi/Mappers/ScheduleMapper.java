package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.CustomerDTO;
import com.production.ehayvanbackendapi.DTO.ScheduleDTO;
import com.production.ehayvanbackendapi.Entities.Customer;
import com.production.ehayvanbackendapi.Entities.Schedule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    private final ModelMapper modelMapper;

    public ScheduleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(Schedule.class, ScheduleDTO.class).addMappings(
                mapper -> {
                    mapper.skip(ScheduleDTO::setMedications);
                }
        );
    }

    public ScheduleDTO convertToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDTO.class);
    }

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        return modelMapper.map(scheduleDTO, Schedule.class);
    }
}

