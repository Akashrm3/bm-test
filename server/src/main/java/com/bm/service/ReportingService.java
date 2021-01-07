package com.bm.service;

import com.bm.common.VaccinationStatus;
import com.bm.model.Schedule;
import com.bm.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ReportingService reportingService;

    /**
     * Get a list of all applied vaccination per branch
     *
     * @return
     */
    @GetMapping("/reporting/vaccination-completed")
    public List<Schedule> getAllCompletedVaccination() {
        return scheduleRepository.findAllByVaccinationStatus(VaccinationStatus.COMPLETE);
    }

    public List<Schedule> getAllByDate(Date date) {
        return scheduleRepository.findAllByAppointmentDate(date);
    }

    public List<Schedule> getAllBetweenDate(Date startDate, Date endDate) {
        return scheduleRepository.findAllByAppointmentDateBetween(startDate, endDate);
    }
}
