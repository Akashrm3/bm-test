package com.bm.service;

import com.bm.exception.ResourceNotFoundException;
import com.bm.model.Branch;
import com.bm.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long branchId)
            throws ResourceNotFoundException {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + branchId));
        return branch;
    }

    public Branch createBranch(Branch branch) {
        branch.getVaccine().forEach(e -> {
            e.setBranch(branch);
        });
        return branchRepository.save(branch);
    }

    public Branch updateBranch(Long branchId,
                               Branch branchDetails) throws ResourceNotFoundException {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + branchId));

        branch.setAddress(branchDetails.getAddress());
        branch.setBranchManager(branchDetails.getBranchManager());
        branch.setBranchName(branchDetails.getBranchName());
        branch.setContactNumber(branchDetails.getContactNumber());
        branch.setEmailAddress(branchDetails.getEmailAddress());
        branch.setVaccine(branch.getVaccine());

        final Branch updatedBranch = branchRepository.save(branch);
        return updatedBranch;
    }

    public Map<String, Boolean> deleteBranch(@PathVariable(value = "id") Long branchId)
            throws ResourceNotFoundException {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + branchId));

        branchRepository.delete(branch);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public int getAvailableSlots(Long branchId) throws ResourceNotFoundException {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + branchId));
        return branch.getSlotAvailable();
    }
}
