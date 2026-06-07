package com.hire.smart.hiring.service;

import com.hire.smart.hiring.dto.ApplicationRequest;
import com.hire.smart.hiring.dto.StatusUpdateRequest;
import com.hire.smart.hiring.model.Job;
import com.hire.smart.hiring.model.JobApplication;
import com.hire.smart.hiring.model.User;
import com.hire.smart.hiring.repository.JobApplicationRepository;
import com.hire.smart.hiring.repository.JobRepository;
import com.hire.smart.hiring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobApplication apply(Long jobId, ApplicationRequest request, String candidateEmail) {
        User candidate = userRepository.findByEmail(candidateEmail)
                .orElseThrow(() -> new RuntimeException("Candidate not found!"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found!"));

        if (applicationRepository.existsByCandidateIdAndJobId(candidate.getId(), jobId)) {
            throw new RuntimeException("Already applied to this job!");
        }

        JobApplication application = new JobApplication();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setResumeLink(request.getResumeLink());
        application.setStatus("APPLIED");

        return applicationRepository.save(application);
    }

    public List<JobApplication> getMyApplications(String candidateEmail) {
        User candidate = userRepository.findByEmail(candidateEmail)
                .orElseThrow(() -> new RuntimeException("Candidate not found!"));
        return applicationRepository.findByCandidateId(candidate.getId());
    }

    public List<JobApplication> getApplicationsForJob(Long jobId, String recruiterEmail) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found!"));

        if (!job.getRecruiter().getEmail().equals(recruiterEmail)) {
            throw new RuntimeException("Unauthorized!");
        }

        return applicationRepository.findByJobId(jobId);
    }

    public JobApplication updateStatus(Long applicationId, StatusUpdateRequest request, String recruiterEmail) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found!"));

        if (!application.getJob().getRecruiter().getEmail().equals(recruiterEmail)) {
            throw new RuntimeException("Unauthorized!");
        }

        application.setStatus(request.getStatus());
        return applicationRepository.save(application);
    }

    public void withdraw(Long applicationId, String candidateEmail) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found!"));

        if (!application.getCandidate().getEmail().equals(candidateEmail)) {
            throw new RuntimeException("Unauthorized!");
        }

        applicationRepository.deleteById(applicationId);
    }

    public Map<String, Long> getCandidateDashboard(String candidateEmail) {
        User candidate = userRepository.findByEmail(candidateEmail)
                .orElseThrow(() -> new RuntimeException("Candidate not found!"));

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", applicationRepository.countByCandidateId(candidate.getId()));
        stats.put("shortlisted", applicationRepository.countByCandidateIdAndStatus(candidate.getId(), "SHORTLISTED"));
        stats.put("selected", applicationRepository.countByCandidateIdAndStatus(candidate.getId(), "SELECTED"));
        stats.put("rejected", applicationRepository.countByCandidateIdAndStatus(candidate.getId(), "REJECTED"));
        return stats;
    }
}