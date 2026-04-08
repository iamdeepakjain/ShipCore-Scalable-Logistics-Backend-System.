package com.example.demo.controller;

import com.example.demo.entity.Partner;
import com.example.demo.service.PartnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    // Create Partner
    @PostMapping
    public Partner createPartner(@RequestBody Partner partner) {
        return partnerService.savePartner(partner);
    }

    // Get All Partners
    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }
}