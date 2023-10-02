package com.example.deliverywebapp.services;

import com.example.deliverywebapp.models.deliveryPerson;
import com.example.deliverywebapp.repositories.deliveryPersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service

public class DistanceCalculatorImpl {
    private final com.example.deliverywebapp.repositories.deliveryPersonRepository deliveryPersonRepository;

    public DistanceCalculatorImpl(deliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    private static final String OPEN_STREET_MAP_API_URL = "https://nominatim.openstreetmap.org/search?q={location}&format=json";
    static ObjectMapper objectMapper = new ObjectMapper();

    public double calculateDistance(String location1, String location2) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url1 = OPEN_STREET_MAP_API_URL.replace("{location}", location1);
        String url2 = OPEN_STREET_MAP_API_URL.replace("{location}", location2);
        double[] coordinates1 = getCoordinates(restTemplate.getForObject(url1, String.class));
        double[] coordinates2 = getCoordinates(restTemplate.getForObject(url2, String.class));
        return distance(coordinates1[0], coordinates1[1], coordinates2[0], coordinates2[1]);
    }

    private static double[] getCoordinates(String response) throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(response);
        double lat = node.get(0).get("lat").asDouble();
        double lon = node.get(0).get("lon").asDouble();
        return new double[] { lat, lon };
        // Parse the JSON response to get the latitude and longitude coordinates
        // You can use a JSON parsing library like Jackson or Gson for this
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius (km)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c * 1000; // Convert to meters
        return d;
    }

    public deliveryPerson findNearestDeliveryPersonByLocationName(String location) throws JsonProcessingException {
        List<deliveryPerson> allDeliveryPersons = deliveryPersonRepository.findAll();
        deliveryPerson nearestPerson= null;
        double minDistance = Double.MIN_VALUE;

       for (deliveryPerson person : allDeliveryPersons ) {
           double distance = calculateDistance(location, person.getDeliveryPersonLocation());
           if (distance < minDistance) {
               minDistance = distance;
               nearestPerson = person;
           }
       }
       return nearestPerson;
    }

}
