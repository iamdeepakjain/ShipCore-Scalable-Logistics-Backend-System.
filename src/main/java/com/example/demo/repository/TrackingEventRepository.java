package com.example.demo.repository;

import com.example.demo.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
    List<TrackingEvent> findByPackageIdOrderByTimestampAsc(Long packageId);
}
