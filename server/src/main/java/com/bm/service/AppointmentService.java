package com.bm.service;

import com.bm.common.ScheduleStatus;
import com.bm.common.VaccinationStatus;
import com.bm.exception.ResourceNotFoundException;
import com.bm.model.Schedule;
import com.bm.model.Branch;
import com.bm.repository.AppointmentRepository;
import com.bm.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Schedule saveAppointment(Schedule schedule) {
        return appointmentRepository.save(schedule);
    }


    public void updateBranch(Long appointmentId, Long branchId) throws ResourceNotFoundException {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + branchId));
        if(branch.getSlotAvailable()<1){
            throw new ResourceNotFoundException("No Slots available");
        }
        branch.setSlotAvailable(branch.getSlotAvailable() - 1);
        branchRepository.save(branch);
    }

    public Schedule getAppointmentById(Long id) throws ResourceNotFoundException {
        Schedule schedule = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + id));
        return schedule;
    }

    public List<Schedule> getAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Schedule> getAppointmentsByBranchId(Long id) {
        return appointmentRepository.findAllByBranchId(id);
    }

    public Schedule completeSchedule(Long id) throws ResourceNotFoundException {
        Schedule schedule = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + id));
        schedule.setStatus(ScheduleStatus.COMPLETE);
        schedule.setVaccinationStatus(VaccinationStatus.COMPLETE);
        schedule.setTotalAmount(Long.parseLong("700"));
        appointmentRepository.save(schedule);

        //--------------- create Invoice
        return schedule;
    }
}
