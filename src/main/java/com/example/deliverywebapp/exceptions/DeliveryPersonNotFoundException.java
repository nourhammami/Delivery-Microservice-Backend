package com.example.deliverywebapp.exceptions;

public class DeliveryPersonNotFoundException extends RuntimeException{
    public DeliveryPersonNotFoundException(Long  id) { super("Could not found the delivery person with id "+ id);}
}
