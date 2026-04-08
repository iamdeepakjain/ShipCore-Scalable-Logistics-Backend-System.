package com.example.demo.controller;

import com.example.demo.entity.Package;
import com.example.demo.service.PackageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    // CREATE
    @PostMapping
    public Package createPackage(@RequestBody Package pkg) {
        return packageService.createPackage(pkg);
    }

    // GET (ROLE BASED)
    @GetMapping
    public List<Package> getPackages(
            @RequestParam String role,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long partnerId,
            @RequestParam(required = false) Long hubId
    ) {
        return packageService.getPackages(role, userId, partnerId, hubId);
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public Package updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return packageService.updateStatus(id, status);
    }

    // CANCEL PACKAGE (USER ONLY)
    @PutMapping("/{id}/cancel")
    public Package cancelPackage(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        return packageService.cancelPackage(id, userId);
    }
}