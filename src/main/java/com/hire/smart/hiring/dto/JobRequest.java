package com.hire.smart.hiring.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class JobRequest {
    private String title;
    private String description;
    private String location;
    private String salaryRange;
    private LocalDate openedDate;
    private LocalDate closingDate;
}
