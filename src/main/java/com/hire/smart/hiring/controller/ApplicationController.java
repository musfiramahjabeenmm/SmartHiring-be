package com.hire.smart.hiring.controller;

import com.hire.smart.hiring.dto.ApplicationRequest;
import com.hire.smart.hiring.dto.StatusUpdateRequest;
import com.hire.smart.hiring.model.JobApplication;
import com.hire.smart.hiring.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<JobApplication> apply(
            @PathVariable Long jobId,
            @RequestBody ApplicationRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(applicationService.apply(jobId, request, email));
    }

    @GetMapping("/my")
    public ResponseEntity<List<JobApplication>> getMyApplications(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(applicationService.getMyApplications(email));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getApplicationsForJob(
            @PathVariable Long jobId,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(applicationService.getApplicationsForJob(jobId, email));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(applicationService.updateStatus(id, request, email));
    }

    @DeleteMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(
            @PathVariable Long id,
            @AuthenticationPrincipal String email) {
        applicationService.withdraw(id, email);
        return ResponseEntity.ok("Application withdrawn");
    }

    @GetMapping("/dashboard/candidate")
    public ResponseEntity<Map<String, Long>> getCandidateDashboard(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(applicationService.getCandidateDashboard(email));
    }
}