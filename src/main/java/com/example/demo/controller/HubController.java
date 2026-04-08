package com.example.demo.controller;

import com.example.demo.entity.Hub;
import com.example.demo.service.HubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hubs")
public class HubController {

    private final HubService hubService;

    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    // Create Hub
    @PostMapping
    public Hub createHub(@RequestBody Hub hub) {
        return hubService.saveHub(hub);
    }

    // Get All Hubs
    @GetMapping
    public List<Hub> getAllHubs() {
        return hubService.getAllHubs();
    }
}