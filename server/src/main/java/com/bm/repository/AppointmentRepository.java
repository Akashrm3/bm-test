package com.bm.repository;

import com.bm.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByBranchId(Long id);
}
