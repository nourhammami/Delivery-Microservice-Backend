package com.example.deliverywebapp.controllers;

import com.example.deliverywebapp.models.deliveryPerson;
import com.example.deliverywebapp.services.DistanceCalculatorImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DistanceController {

    @Autowired
    DistanceCalculatorImpl service;
    @GetMapping("/distance")
    public double calculateDistance(@RequestParam String location1, @RequestParam String location2) throws JsonProcessingException {
        return  service.calculateDistance(location1,location2);
    }

    @GetMapping("/findNearestDeliveryPerson")
    public ResponseEntity<deliveryPerson> findNearestDeliveryPerson(@RequestParam String locationName) throws JsonProcessingException {
        deliveryPerson nearestPerson = service.findNearestDeliveryPersonByLocationName(locationName);
        if (nearestPerson == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nearestPerson);
    }
}
