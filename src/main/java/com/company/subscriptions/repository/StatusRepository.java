package com.company.subscriptions.repository;

import com.company.subscriptions.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
