package com.bm.controller;

import com.bm.model.Branch;
import com.bm.model.Vaccine;
import com.bm.repository.BranchRepository;
import com.bm.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private BranchRepository branchRepository;

    @GetMapping("/vaccine")
    public List<Vaccine> getAllVaccine() {
        return vaccineService.getAllVaccine();
    }

    /**
     * Get a list of all available vaccines per branch
     *
     * @param branchId
     * @return
     */
    @GetMapping("/vaccine/branch/{id}")
    public Set<Vaccine> getAllVaccine(@PathVariable(value = "id") Long branchId) {
        Optional<Branch> branch = branchRepository.findById(branchId);
        if (branch.isPresent()) {
            return branch.get().getVaccine();
        } else {
            return null;
        }
    }

}
