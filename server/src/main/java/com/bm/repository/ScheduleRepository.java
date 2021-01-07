package com.bm.repository;

import com.bm.common.VaccinationStatus;
import com.bm.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByVaccinationStatus(VaccinationStatus complete);

    List<Schedule> findAllByAppointmentDate(Date date);

    List<Schedule> findAllByAppointmentDateBetween(Date startDate, Date endDate);
}
