package org.example.booking;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class TripRequestPojo {
    private int numDaysInEachCity;
    private int totalBudget;
    private Map<String, List<Integer>> pricesPerCity;
}
