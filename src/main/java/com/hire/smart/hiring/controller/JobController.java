package com.hire.smart.hiring.controller;

import com.hire.smart.hiring.dto.JobRequest;
import com.hire.smart.hiring.model.Job;
import com.hire.smart.hiring.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;

    @PostMapping("/post")
    public ResponseEntity<Job> postJob(
            @RequestBody JobRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(jobService.postJob(request, email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/my")
    public ResponseEntity<List<Job>> getMyJobs(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(jobService.getMyJobs(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id,
            @AuthenticationPrincipal String email) {
        jobService.deleteJob(id, email);
        return ResponseEntity.ok("Job deleted successfully");
    }
}