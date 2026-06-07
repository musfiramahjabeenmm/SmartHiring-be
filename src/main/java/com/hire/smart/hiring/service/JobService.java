package com.hire.smart.hiring.service;

import com.hire.smart.hiring.dto.JobRequest;
import com.hire.smart.hiring.model.Job;
import com.hire.smart.hiring.model.User;
import com.hire.smart.hiring.repository.JobRepository;
import com.hire.smart.hiring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public Job postJob(JobRequest request, String recruiterEmail) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found!"));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalaryRange(request.getSalaryRange());
        job.setOpenedDate(request.getOpenedDate());
        job.setClosingDate(request.getClosingDate());
        job.setRecruiter(recruiter);

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getMyJobs(String recruiterEmail) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found!"));
        return jobRepository.findByRecruiterId(recruiter.getId());
    }

    public void deleteJob(Long jobId, String recruiterEmail) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found!"));

        if (!job.getRecruiter().getEmail().equals(recruiterEmail)) {
            throw new RuntimeException("Unauthorized!");
        }

        jobRepository.deleteById(jobId);
    }
}