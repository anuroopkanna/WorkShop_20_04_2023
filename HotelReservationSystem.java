package com.BridgeLabs;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HotelReservationSystem {
    static Hotel lakewood =new Hotel ("lakewood",3,110,90,80,80);
    static Hotel bridgewood=new Hotel("Bridgewood",4,160,60,110,50);
    static  Hotel ridgewood =new Hotel("Ridgewood",5,220,150,100,40);

    public static void main(String[] args) {
        LocalDate startDate= LocalDate.of(2023,4,24);
        LocalDate endDate = LocalDate.of(2023,4,30);
        boolean isRewardCostumer=true;
        String[] daysOfWeek={"Saturday","sunday"};
        List<Hotel>  hotels = new ArrayList<>();
        hotels.add(lakewood);
        hotels.add(bridgewood);
        hotels.add(ridgewood);
        List<Integer> costs =hotels.stream().map(hotel -> hotel.calculateCost(isRewardCostumer,startDate, endDate,daysOfWeek)).collect(Collectors.toList());
        int cheepestIndex = costs.indexOf(costs.stream().min(Comparator.naturalOrder()).orElseThrow());
        System.out.println("The cheapest hotel: " +hotels.get(cheepestIndex).getName());
    }
}
class Hotel{
    private String name;
    private int rating;
    private int weekdayRegularRate;
    private int weekendRegularRate;
    private int weekdayRewardRate;
    private int weekendRewardRate;

    public Hotel(String name, int rating, int weekdayRegularRate, int weekendRegularRate, int weekdayRewardRate, int weekendRewardRate) {
        this.name = name;
        this.rating = rating;
        this.weekdayRegularRate = weekdayRegularRate;
        this.weekendRegularRate = weekendRegularRate;
        this.weekdayRewardRate = weekdayRewardRate;
        this.weekendRewardRate = weekendRewardRate;
    }

    public String getName() {
        return name;
    }
    public  int calculateCost(boolean isRewardCustomer,LocalDate startDate, LocalDate endDate,String[] daysOfWeek) {

        int totalCost = 0;
        DayOfWeek dayOfWeek = null;
        for (LocalDate date = startDate; date.isBefore(endDate); date.plus(1, ChronoUnit.DAYS)) {
            dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday")) {
                if (isRewardCustomer) {
                    totalCost += weekdayRewardRate;
                } else {
                    totalCost += weekendRegularRate;
                }
                if (isRewardCustomer) {
                    totalCost += weekdayRewardRate;
                } else {
                    totalCost += weekdayRegularRate;
                }
            }
        }
        //int dayofWeekValue = dayOfWeek.getValue();// 1 for monday 2 for tuesday ......
        //return dayofWeekValue;
        return totalCost;
    }
}
//for (LocalDate date=startDate;date.isBefore(endDate;date.plus(1, ChronoUnit.DAYS))){