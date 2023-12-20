package com.production.ehayvanbackendapi.Controllers;

import com.production.ehayvanbackendapi.DTO.ScheduleDTO;
import com.production.ehayvanbackendapi.Services.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Integer id) {
        ScheduleDTO scheduleDTO = scheduleService.getScheduleById(id);

        if (scheduleDTO != null) {
            return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping
//    public ResponseEntity<ScheduleDTO> saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
//        ScheduleDTO savedSchedule = scheduleService.saveSchedule(scheduleDTO);
//        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
//    }


}

