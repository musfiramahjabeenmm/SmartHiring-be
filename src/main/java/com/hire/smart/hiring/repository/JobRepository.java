package com.hire.smart.hiring.repository;

import com.hire.smart.hiring.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>
{
    List<Job> findByRecruiterId(Long recruiterId);
}
