package com.example.demo12.repositories;

import com.example.demo12.entities.DailyDomains;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<DailyDomains, Long> {
}