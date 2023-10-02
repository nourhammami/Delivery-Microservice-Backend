package com.example.deliverywebapp.controllers;

import com.example.deliverywebapp.models.Marker;
import com.example.deliverywebapp.repositories.MarkerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/markers")
public class MarkerController {
    private final MarkerRepository markerRepository;

    public MarkerController(MarkerRepository markerRepository) {
        this.markerRepository = markerRepository;
    }

    @GetMapping
    public List<Marker> getAllMarkers() {
        return markerRepository.findAll();
    }
}
