package com.hire.smart.hiring.dto;

import lombok.Data;

@Data
public class StatusUpdateRequest {
    private String status; // SHORTLISTED, SELECTED, REJECTED
}