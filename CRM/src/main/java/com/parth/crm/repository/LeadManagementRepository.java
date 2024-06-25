package com.parth.crm.repository;

import com.parth.crm.models.LeadManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadManagementRepository
        extends JpaRepository<LeadManagement, Integer> {
}
