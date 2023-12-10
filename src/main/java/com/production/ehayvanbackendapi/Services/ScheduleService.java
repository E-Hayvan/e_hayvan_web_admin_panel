package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.Schedule;
import com.production.ehayvanbackendapi.Repositories.ScheduleRepository;

import java.util.List;

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }
}
