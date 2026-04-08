package com.example.demo.repository;

import com.example.demo.entity.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubRepository extends JpaRepository<Hub, Long> {
}