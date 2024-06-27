package com.parth.crm.repository;

import com.parth.crm.models.LeadManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeadManagementRepository
        extends JpaRepository<LeadManagement, String> {
    Optional<LeadManagement>  findByLeadId(String leadId);
}
