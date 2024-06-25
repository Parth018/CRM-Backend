package com.parth.crm.repository;

import com.parth.crm.models.InventoryManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryManagementRepository
        extends JpaRepository<InventoryManagement, Integer> {
}
