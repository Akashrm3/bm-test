package com.bm.controller;

import com.bm.model.Branch;
import com.bm.model.Schedule;
import com.bm.model.Vaccine;
import com.bm.repository.BranchRepository;
import com.bm.service.ReportingService;
import com.bm.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private BranchRepository branchRepository;

    @GetMapping("report/vaccination-completed")
    public List<Schedule> getAllCompletedVaccination() {
        return reportingService.getAllCompletedVaccination();
    }

    @GetMapping("report/vaccination-by-date")
    public List<Schedule> getAllByDate(@PathVariable(value = "date") Date date) {
        return reportingService.getAllByDate(date);
    }

    @GetMapping("report/vaccination-between-date")
    public List<Schedule> getAllBetweenDate(@PathVariable(value = "date") Date startDate,
                                            @PathVariable(value = "date") Date endDate) {
        return reportingService.getAllBetweenDate(startDate, endDate);
    }

}
