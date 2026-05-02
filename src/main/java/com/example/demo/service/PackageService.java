package com.example.demo.service;

import com.example.demo.entity.Package;
import com.example.demo.entity.ShipmentStatus;
import com.example.demo.entity.TrackingEvent;
import com.example.demo.repository.PackageRepository;
import com.example.demo.repository.TrackingEventRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackageService {

    private final PackageRepository packageRepository;
    private final TrackingEventRepository trackingEventRepository;

    public PackageService(
            PackageRepository packageRepository,
            TrackingEventRepository trackingEventRepository
    ) {
        this.packageRepository = packageRepository;
        this.trackingEventRepository = trackingEventRepository;
    }

    // Create Package
    public Package createPackage(Package pkg) {
        pkg.setStatus("CREATED");

        Package saved = packageRepository.save(pkg);

        createTrackingEvent(
                saved.getId(),
                "CREATED",
                pkg.getPickupAddress(),
                "Package created"
        );

        return saved;
    }

    // ROLE BASED FETCH
    public List<Package> getPackages(String role, Long userId, Long partnerId, Long hubId) {

        if (role == null) {
            throw new RuntimeException("Role is required");
        }

        switch (role.toUpperCase()) {

            case "ADMIN":
                return packageRepository.findAll();

            case "USER":
                if (userId == null) {
                    throw new RuntimeException("userId is required");
                }
                return packageRepository.findByUserId(userId);

            case "PARTNER":
                if (partnerId == null) {
                    throw new RuntimeException("partnerId is required");
                }
                return packageRepository
                        .findByPickupPartnerIdOrDeliveryPartnerId(partnerId, partnerId);

            case "HUB":
                if (hubId == null) {
                    throw new RuntimeException("hubId is required");
                }
                return packageRepository
                        .findBySourceHubIdOrDestinationHubId(hubId, hubId);

            default:
                throw new RuntimeException("Invalid role");
        }
    }

    // Update Status
    public Package updateStatus(Long packageId, String status) {
        Package pkg = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        pkg.setStatus(status);

        Package saved = packageRepository.save(pkg);

        createTrackingEvent(
                saved.getId(),
                status,
                null,
                "Status updated"
        );

        return saved;
    }

    // Cancel Package
    public Package cancelPackage(Long packageId, Long userId) {
        Package pkg = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        if (pkg.getUser() != null && !pkg.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to cancel this package");
        }

        pkg.setStatus("CANCELLED");

        Package saved = packageRepository.save(pkg);

        createTrackingEvent(
                saved.getId(),
                "CANCELLED",
                null,
                "Package cancelled"
        );

        return saved;
    }

    // Tracking History
    public List<TrackingEvent> getPackageHistory(Long packageId) {
        return trackingEventRepository.findByPackageIdOrderByTimestampAsc(packageId);
    }

    // Analytics
    public Map<String, Object> getPackageAnalytics(Long packageId) {
        Package pkg = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        List<TrackingEvent> history =
                trackingEventRepository.findByPackageIdOrderByTimestampAsc(packageId);

        long trackingEvents = history.size();

        double hoursSinceCreated =
                Duration.between(
                        pkg.getCreatedAt(),
                        LocalDateTime.now()
                ).toMinutes() / 60.0;

        Map<String, Object> result = new HashMap<>();
        result.put("packageId", pkg.getId());
        result.put("trackingEvents", trackingEvents);
        result.put("currentStatus", pkg.getStatus());
        result.put("hoursSinceCreated", hoursSinceCreated);

        return result;
    }

    // AI Risk Prediction
    public Map<String, Object> getPackageRiskPrediction(Long packageId) {
        Package pkg = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        List<TrackingEvent> history =
                trackingEventRepository.findByPackageIdOrderByTimestampAsc(packageId);

        double hoursSinceCreated =
                Duration.between(
                        pkg.getCreatedAt(),
                        LocalDateTime.now()
                ).toMinutes() / 60.0;

        long trackingEvents = history.size();

        String risk;
        double confidence;
        String reason;

        if ("DELIVERED".equalsIgnoreCase(pkg.getStatus())) {
            risk = "NONE";
            confidence = 1.0;
            reason = "Shipment already delivered";
        } else if (hoursSinceCreated < 2 && trackingEvents >= 2) {
            risk = "LOW";
            confidence = 0.85;
            reason = "Shipment has recent tracking activity";
        } else if (hoursSinceCreated < 6 && trackingEvents >= 1) {
            risk = "MEDIUM";
            confidence = 0.65;
            reason = "Shipment is moving but requires observation";
        } else {
            risk = "HIGH";
            confidence = 0.90;
            reason = "Shipment has low activity or possible delay";
        }

        Map<String, Object> result = new HashMap<>();
        result.put("packageId", pkg.getId());
        result.put("delayRisk", risk);
        result.put("confidence", confidence);
        result.put("reason", reason);

        return result;
    }

    // Helper
    private void createTrackingEvent(
            Long packageId,
            String status,
            String location,
            String remarks
    ) {
        TrackingEvent event = new TrackingEvent();

        event.setPackageId(packageId);
        event.setStatus(ShipmentStatus.valueOf(status));
        event.setLocation(location);
        event.setRemarks(remarks);
        event.setTimestamp(LocalDateTime.now());

        trackingEventRepository.save(event);
    }
}