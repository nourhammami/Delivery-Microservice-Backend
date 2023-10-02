package com.example.deliverywebapp.repositories;

import com.example.deliverywebapp.models.deliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deliveryPersonRepository extends JpaRepository<deliveryPerson, Long> {
}