package org.example.booking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRunner {


    public void run() {
        TripRequestPojo tripRequest = new TripRequestPojo();

        tripRequest.setNumDaysInEachCity(2);
        tripRequest.setTotalBudget(390);

        Map<String, List<Integer>> pricesPerCity = new HashMap<>();
        pricesPerCity.put("Paris", Arrays.asList(10, 20, 50, 80, 100, 20));
        pricesPerCity.put("LA", Arrays.asList(90, 10, 40, 90, 12, 11));
        pricesPerCity.put("Amsterdam", Arrays.asList(70, 20, 10, 30, 20, 80));

        tripRequest.setPricesPerCity(pricesPerCity);

        TripOrganizer tripOrganizer = new TripOrganizer();
        tripOrganizer.calculatePossibleItinerary(tripRequest);
    }
}
