package com.bm.controller;

import com.bm.common.ScheduleStatus;
import com.bm.common.VaccinationStatus;
import com.bm.exception.ResourceNotFoundException;
import com.bm.model.Schedule;
import com.bm.model.AppointmentDTO;
import com.bm.repository.AppointmentRepository;
import com.bm.repository.BranchRepository;
import com.bm.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AppointmentService appointmentService;

    /**
     * Schedule vaccination timeslot (15 minutes).
     * to a provided branch.
     *
     * @param appointmentDTO
     * @return
     * @throws ResourceNotFoundException
     */
    @PostMapping("/schedule")
    public Schedule createAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        Schedule schedule = new Schedule();
        schedule.setAppointmentDate(new Date());
        schedule.setStartTime(appointmentDTO.getStartTime());
        schedule.setEndTime(appointmentDTO.getEndTime());
        schedule.setCustomerName(appointmentDTO.getCustomerName());
        schedule.setBranchId(appointmentDTO.getBranchId());
        schedule.setCustomerAddress(appointmentDTO.getCustomerAddress());
        schedule.setCustomerEmail(appointmentDTO.getCustomerEmail());
        schedule.setCustomerMobile(appointmentDTO.getCustomerMobile());
        schedule.setStatus(ScheduleStatus.AVAILABLE);
        schedule.setVaccinationStatus(VaccinationStatus.INCOMPLETE);
        schedule.setStatus(ScheduleStatus.AVAILABLE);
        Schedule savedSchedule = appointmentService.saveAppointment(schedule);
        appointmentService.updateBranch(savedSchedule.getId(), appointmentDTO.getBranchId());
        return savedSchedule;
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> getBranchById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/schedule/complete/{id}")
    public ResponseEntity<Schedule> completeSchedule(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(appointmentService.completeSchedule(id));
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> getAppointments()
            throws ResourceNotFoundException {
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @GetMapping("/schedule/branch/{id}")
    public ResponseEntity<List<Schedule>> getAppointmentsByBranchId(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(appointmentService.getAppointmentsByBranchId(id));
    }

}
