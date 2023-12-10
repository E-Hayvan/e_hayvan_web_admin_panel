package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.Appointment;
import com.production.ehayvanbackendapi.Repositories.AppointmentRepository;

import java.util.List;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
}
