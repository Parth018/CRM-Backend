package com.parth.crm.repository;

import com.parth.crm.models.SalesTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesTaskRepository
        extends JpaRepository<SalesTasks, Integer> {
}
