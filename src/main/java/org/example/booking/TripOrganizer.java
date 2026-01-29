package org.example.java;

import java.util.*;

public class TripOrganizer {

    static class Pair {
        public int day;
       public String city;

        public Pair(int d, String c) {
            day = d;
            city = c;
        }
    }


    List<List<Pair>> allPossibleCities = new ArrayList<>();
    int n = 0;
    public void calculatePossibleItinerary(TripRequestPojo tripRequest) {

        HashMap<String, List<Integer>> cityToTotalPricePerStayMap = calculatePrice(tripRequest);

        Map.Entry<String, List<Integer>> firstEntry = tripRequest.getPricesPerCity().entrySet().iterator().next();
        List<Integer> list = cityToTotalPricePerStayMap.get(firstEntry.getKey());
        n = list.size();

        for (int i = 0;i<list.size();i++) {
            LinkedHashSet<Integer> visitedDaysWindow = new LinkedHashSet<>();
            LinkedHashSet<String> visitedCities = new LinkedHashSet<>();
            visitedDaysWindow.add(i);
            visitedCities.add(firstEntry.getKey());
            List<Pair> currAns = new ArrayList<>();
            currAns.add(new Pair(i,firstEntry.getKey()));
          //  System.out.println(i);
            util(currAns,tripRequest,cityToTotalPricePerStayMap,tripRequest.getTotalBudget()-list.get(i),visitedDaysWindow, visitedCities);
        }


        print(allPossibleCities);
    }
    void print(List<List<Pair>> allPossibleCities) {

        System.out.println("printing ans of size: "+ allPossibleCities.size());
        for (List<Pair> allPossibleCity : allPossibleCities) {
         allPossibleCity.forEach(x -> System.out.print("{ " + x.city+", "+x.day +"} , "));
            System.out.println();
        }
    }



    void util(List<Pair> currAns, TripRequestPojo tripRequest, HashMap<String, List<Integer>> cityToTotalPricePerStayMap, int remainingBudget, LinkedHashSet<Integer> visitedDaysWindow, LinkedHashSet<String> visitedCities) {

        if (visitedDaysWindow.size() == n) {

            if (remainingBudget >= 0) {
                currAns.sort(Comparator.comparingInt(a -> a.day));
                allPossibleCities.add(new ArrayList<>(currAns));
            }
            return;
        }

// 3. Optimization: If we already overspent, stop searching this path immediately
        if (remainingBudget < 0) {
            return;
        }
        for (Map.Entry<String,List<Integer>> entry: cityToTotalPricePerStayMap.entrySet()) {

            String city = entry.getKey();
            List<Integer> priceList = entry.getValue();
            if (!visitedCities.contains(city)) {
                visitedCities.add(city);

                for (int i = 0;i<priceList.size();i++) {

                 Pair circuity = new Pair(i,city);
                    if (!visitedDaysWindow.contains(i)) {
                        visitedDaysWindow.add(i);
                        if (remainingBudget-priceList.get(i) >=0) {
                            currAns.add(circuity);
                            util(currAns, tripRequest, cityToTotalPricePerStayMap, remainingBudget - priceList.get(i), visitedDaysWindow, visitedCities);
                            currAns.remove(currAns.size()-1);
                        }
                        visitedDaysWindow.remove(i);
                    }
                }
                visitedCities.remove(city);
            }
        }
    }

    HashMap<String, List<Integer>> calculatePrice(TripRequestPojo tripRequest) {

        HashMap<String, List<Integer>> cityToTotalPricePerStayMap = new HashMap<>();

        Map<String, List<Integer>> pricesPerCity = tripRequest.getPricesPerCity();

        int noOfDays = tripRequest.getNumDaysInEachCity();

        for (Map.Entry<String,List<Integer>> entry: pricesPerCity.entrySet()) {

            List<Integer> priceList = entry.getValue();
            List<Integer> totalPriceList = cityToTotalPricePerStayMap.getOrDefault(entry.getKey(), new ArrayList<>());

            for (int i = 0;i<priceList.size();i+=noOfDays) {
                int totalPrice = 0;

                for (int j = 0;j<noOfDays;j++) {
                    totalPrice+=priceList.get(i+j);
                }
                totalPriceList.add(totalPrice);
            }

            cityToTotalPricePerStayMap.put(entry.getKey(), totalPriceList);
        }
        return cityToTotalPricePerStayMap;
    }


}
