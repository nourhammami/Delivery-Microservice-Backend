package com.example.deliverywebapp.repositories;

import com.example.deliverywebapp.models.delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deliveryRepository extends JpaRepository<delivery, Long> {
}