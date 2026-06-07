package com.hire.smart.hiring.repository;

import com.hire.smart.hiring.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>
{
    List<JobApplication> findByCandidateId(Long candidateId);
    List<JobApplication> findByJobId(Long jobId);
    Optional<JobApplication> findByCandidateIdAndJobId(Long candidateId, Long jobId);
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);
    long countByCandidateId(Long candidateId);
    long countByCandidateIdAndStatus(Long candidateId, String status);
}
