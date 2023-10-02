package com.example.deliverywebapp.exceptions;

public class DeliveryNotFoundException extends RuntimeException{
    public DeliveryNotFoundException(Long  id) { super("Could not found the delivery with id "+ id);}
}
