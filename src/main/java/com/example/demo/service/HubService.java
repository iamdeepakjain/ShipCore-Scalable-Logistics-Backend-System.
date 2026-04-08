package com.example.demo.service;

import com.example.demo.entity.Hub;
import com.example.demo.repository.HubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HubService {

    private final HubRepository hubRepository;

    public HubService(HubRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    public Hub saveHub(Hub hub) {
        return hubRepository.save(hub);
    }

    public List<Hub> getAllHubs() {
        return hubRepository.findAll();
    }
}
