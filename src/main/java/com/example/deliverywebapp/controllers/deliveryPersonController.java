package com.example.deliverywebapp.controllers;

import com.example.deliverywebapp.exceptions.DeliveryPersonNotFoundException;
import com.example.deliverywebapp.models.deliveryPerson;
import com.example.deliverywebapp.repositories.deliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/deliveryPersonController")
@CrossOrigin(origins = "*")
public class deliveryPersonController {
    @Autowired
    private deliveryPersonRepository deliveryPersonRepository;


    @PostMapping("/deliveryPerson")
    deliveryPerson newDeliveryPerson(@RequestBody deliveryPerson newDeliveryPerson) { return deliveryPersonRepository.save(newDeliveryPerson); }

    @GetMapping("/deliverypersons")
    List<deliveryPerson> getAllDeliveryPersons() { return deliveryPersonRepository.findAll(); }

    @GetMapping("/deliveryPerson/{id}")
    deliveryPerson getDeliveryPersonById(@PathVariable Long  id) {
        return deliveryPersonRepository.findById(id).get();
    }


    @PutMapping("/deliveryPerson/{id}")
    deliveryPerson updateDeliveryPerson(@RequestBody deliveryPerson newDeliveryPerson, @PathVariable Long id) {return
            deliveryPersonRepository.findById(id)
                    .map(deliveryPerson ->{
                        deliveryPerson.setDeliveryPersonId(newDeliveryPerson.getDeliveryPersonId());
                        deliveryPerson.setDeliveryPersonLocation(newDeliveryPerson.getDeliveryPersonLocation());
                        deliveryPerson.setDeliveryPersonName(newDeliveryPerson.getDeliveryPersonName());


                        return deliveryPersonRepository.save(deliveryPerson); }).orElseThrow(() -> new DeliveryPersonNotFoundException(id));
    }
    @DeleteMapping("/deliveryPerson/{id}")
    String deleteDeliveryPerson(@PathVariable Long id){

        deliveryPersonRepository.deleteById(id);
        return "Delivery Person with id "+id+" has been deleted with success. ";
    }
}
