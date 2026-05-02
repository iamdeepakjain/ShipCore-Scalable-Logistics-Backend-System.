package com.example.demo.controller;

import com.example.demo.entity.Package;
import com.example.demo.entity.TrackingEvent;
import com.example.demo.service.PackageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    // Create Package
    @PostMapping
    public Package createPackage(@RequestBody Package pkg) {
        return packageService.createPackage(pkg);
    }

    // Get Packages
    @GetMapping
    public List<Package> getPackages(
            @RequestParam String role,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long partnerId,
            @RequestParam(required = false) Long hubId
    ) {
        return packageService.getPackages(role, userId, partnerId, hubId);
    }

    // Update Status
    @PutMapping("/{id}/status")
    public Package updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return packageService.updateStatus(id, status);
    }

    // Cancel Package
    @PutMapping("/{id}/cancel")
    public Package cancelPackage(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        return packageService.cancelPackage(id, userId);
    }

    // Tracking History
    @GetMapping("/{id}/history")
    public List<TrackingEvent> getPackageHistory(@PathVariable Long id) {
        return packageService.getPackageHistory(id);
    }

    // Analytics
    @GetMapping("/{id}/analytics")
    public Map<String, Object> getPackageAnalytics(@PathVariable Long id) {
        return packageService.getPackageAnalytics(id);
    }

    // AI Risk Prediction
    @GetMapping("/{id}/ai-risk")
    public Map<String, Object> getPackageRiskPrediction(@PathVariable Long id) {
        return packageService.getPackageRiskPrediction(id);
    }
}