package com.parth.crm.repository;

import com.parth.crm.models.FollowUpReminders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowUpRemindersRepository
        extends JpaRepository<FollowUpReminders, Integer> {
}
