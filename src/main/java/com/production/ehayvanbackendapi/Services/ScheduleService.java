package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.ScheduleDTO;
import com.production.ehayvanbackendapi.Entities.Schedule;
import com.production.ehayvanbackendapi.Mappers.ScheduleMapper;
import com.production.ehayvanbackendapi.Repositories.ScheduleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    public ScheduleDTO getScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        return schedule != null ? scheduleMapper.convertToDto(schedule) : null;
    }

//    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
//        Schedule schedule = scheduleMapper.convertToEntity(scheduleDTO);
//        Schedule savedSchedule = scheduleRepository.save(schedule);
//        return scheduleMapper.convertToDto(savedSchedule);
//    }

    // Other service methods for updating, deleting schedules, etc.
}

