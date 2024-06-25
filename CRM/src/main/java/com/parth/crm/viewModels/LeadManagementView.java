package com.parth.crm.viewModels;

import com.parth.crm.models.Priority;
import com.parth.crm.models.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LeadManagementView {
    private Integer id;
    private String lead_id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String make;
    private String model;
    private Integer year;
}
