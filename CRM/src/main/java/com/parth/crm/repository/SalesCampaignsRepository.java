package com.parth.crm.repository;

import com.parth.crm.models.SalesCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesCampaignsRepository extends
        JpaRepository<SalesCampaign, Integer> {
}
