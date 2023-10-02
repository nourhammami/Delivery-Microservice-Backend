package com.example.deliverywebapp.controllers;


import com.example.deliverywebapp.exceptions.DeliveryNotFoundException;
import com.example.deliverywebapp.models.delivery;
import com.example.deliverywebapp.repositories.deliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryController")
@CrossOrigin(origins = "*")
public class deliveryController {
    @Autowired
    private deliveryRepository deliveryRepository;


    @PostMapping("/delivery")
    delivery newDelivery(@RequestBody delivery newDelivery) { return deliveryRepository.save(newDelivery); }

    @GetMapping("/deliveries")
    List<delivery> getAllDeliveries() { return deliveryRepository.findAll(); }

    @GetMapping("/delivery/{id}")
    delivery getDeliveryById(@PathVariable Long  id) {
        return deliveryRepository.findById(id).get();

    }


    @PutMapping("/delivery/{id}")
    delivery updateDelivery(@RequestBody delivery newDelivery, @PathVariable Long id) {return
            deliveryRepository.findById(id)
                    .map(delivery ->{
                        delivery.setDeliveryId(newDelivery.getDeliveryId());
                        delivery.setDeliveryAddress(newDelivery.getDeliveryAddress());
                        delivery.setDelivered(newDelivery.isDelivered());
                        delivery.setUserId(newDelivery.getUserId());
                        return deliveryRepository.save(delivery); }).orElseThrow(() -> new DeliveryNotFoundException(id));
    }
    @DeleteMapping("/delivery/{id}")
    String deleteDelivery(@PathVariable Long id){

        deliveryRepository.deleteById(id);
        return "Delivery with id "+id+" has been deleted with success. ";
    }
}
