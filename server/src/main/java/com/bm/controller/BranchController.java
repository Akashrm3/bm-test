package com.bm.controller;

import com.bm.exception.ResourceNotFoundException;
import com.bm.model.Branch;
import com.bm.repository.BranchRepository;
import com.bm.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchRepository branchRepository;

    /**
     * Get a list of all branches.
     *
     * @return
     */
    @GetMapping("/branch")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable(value = "id") Long branchId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }

    @PostMapping("/branch")
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    @PutMapping("/branch/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable(value = "id") Long branchId,
                                               @RequestBody Branch branchDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(branchService.updateBranch(branchId, branchDetails));
    }

    @DeleteMapping("/branch/{id}")
    public Map<String, Boolean> deleteBranch(@PathVariable(value = "id") Long branchId)
            throws ResourceNotFoundException {
        return branchService.deleteBranch(branchId);
    }

    /**
     * Get a specific availability by branch.
     *
     * @param branchId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/branch-slot-availability/{id}")
    public ResponseEntity<Integer> getAvailableSlots(@PathVariable(value = "id") Long branchId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(branchService.getAvailableSlots(branchId));
    }

    /**
     * Get available time for a branch in minutes.
     *
     * @param branchId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/branch-time-availability/{id}")
    public ResponseEntity<Integer> getAvailableTimes(@PathVariable(value = "id") Long branchId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(branchService.getAvailableSlots(branchId) * 15);
    }
}
