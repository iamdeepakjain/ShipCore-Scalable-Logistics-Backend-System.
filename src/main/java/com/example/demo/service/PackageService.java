package com.example.demo.service;

import com.example.demo.entity.Package;
import com.example.demo.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    // Create Package
    public Package createPackage(Package pkg) {
        pkg.setStatus("ORDERED");
        return packageRepository.save(pkg);
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
        return packageRepository.save(pkg);
    }

    // Cancel Package (USER)
    public Package cancelPackage(Long packageId, Long userId) {
        Package pkg = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        // Ensure user owns the package
        if (!pkg.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to cancel this package");
        }

        pkg.setStatus("CANCELLED");
        return packageRepository.save(pkg);
    }
}