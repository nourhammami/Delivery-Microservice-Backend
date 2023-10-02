package com.example.deliverywebapp.controllers;


import com.example.deliverywebapp.exceptions.DeliveryNotFoundException;
import com.example.deliverywebapp.exceptions.UserNotFoundException;
import com.example.deliverywebapp.models.user;
import com.example.deliverywebapp.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userController")
@CrossOrigin(origins = "*")

public class userController {

    @Autowired
    private userRepository userRepository;


    @PostMapping("/user")
    user newUser(@RequestBody user newUser) { return userRepository.save(newUser); }

    @GetMapping("/users")
    List<user> getAllUsers() { return userRepository.findAll(); }

    @GetMapping("/user/{id}")
    user getUserById(@PathVariable Long  id) {
        return userRepository.findById(id).get();

    }


    @PutMapping("/user/{id}")
    user updateUser(@RequestBody user newUser, @PathVariable Long id) {return
          userRepository.findById(id)
                    .map(user ->{
                        user.setUserId(newUser.getUserId());
                        user.setUserEmail(newUser.getUserEmail());
                        user.setUserName(newUser.getUserName());
                        user.setDeliveryId(newUser.getDeliveryId());
                        return userRepository.save(user); }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){

        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted with success. ";
    }
}
