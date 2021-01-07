package com.bm.service;

import com.bm.model.Vaccine;
import com.bm.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @GetMapping("/vaccine")
    public List<Vaccine> getAllVaccine() {
        return vaccineRepository.findAll();
    }

}
