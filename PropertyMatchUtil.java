package com.im.tut.utils;

import com.im.tut.models.Property;
import com.im.tut.models.SearchCriteria;

public class PropertyMatchUtil {

    private Property property;
    private SearchCriteria searchCriteria;


    public PropertyMatchUtil(Property property, SearchCriteria searchCriteria) {
        this.property = property;
        this.searchCriteria = searchCriteria;
    }

    public double getMatchPercentage() throws Exception {

        double matchPercentage = 0;
        if(distance(property.getLatitude(),searchCriteria.getLatitude(),property.getLongitude(),searchCriteria.getLongitude() ) < 2){
            matchPercentage+=30;
        }

        if(searchCriteria.getMinBudget()!=searchCriteria.getMaxBudget() && property.getCost()>=searchCriteria.getMinBudget() && property.getCost() <=searchCriteria.getMinBudget()){
            matchPercentage+=30;
        }else if(searchCriteria.getMinBudget() == searchCriteria.getMaxBudget() && budgetSatisfies(property.getCost(),searchCriteria.getMinBudget(),searchCriteria.getMaxBudget(),10)){
            matchPercentage+=30;
        }

        if(property.getBedrooms()>=searchCriteria.getMinBedroom() && property.getBedrooms()<=searchCriteria.getMaxBedroom()){
            matchPercentage+=20;
        }else{
            double diffPercentage = getRoomMatchPercentage(property.getBedrooms(),searchCriteria.getMinBedroom(),searchCriteria.getMaxBedroom());
            if(diffPercentage < 20) {
                matchPercentage = matchPercentage + (20 - diffPercentage); //adding match percentage only with +20/-20 percentage difference else 0
            }
        }

        if(property.getBathrooms()>=searchCriteria.getMinBathroom() && property.getBedrooms()<=searchCriteria.getMaxBathroom()){
            matchPercentage+=20;
        }else{
            double diffPercentage = getRoomMatchPercentage(property.getBathrooms(),searchCriteria.getMinBathroom(),searchCriteria.getMaxBathroom());
            if(diffPercentage < 20) {
                matchPercentage = matchPercentage + (20 - diffPercentage); //adding match percentage only with +20/-20 percentage difference else 0
            }
        }

        return matchPercentage;


    }

    public boolean validMatch() throws Exception{
        //converting logic of distance
        //for a property and requirement to be considered a valid match, distance should be within 10 miles
        if(distance(property.getLatitude(),searchCriteria.getLatitude(),property.getLongitude(),searchCriteria.getLongitude()) < 10){
            if(budgetSatisfies(property.getCost(),searchCriteria.getMinBudget(),searchCriteria.getMaxBudget(),25)){
                if(roomSatisfies(property.getBedrooms(),searchCriteria.getMinBedroom(),searchCriteria.getMaxBedroom()) && roomSatisfies(property.getBathrooms(),searchCriteria.getMinBathroom(),searchCriteria.getMaxBathroom())){
                    return true;
                }
            }

        }
        return false;

    }

    //code shamelessly copied and modified from https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
    public double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance)*0.000621371; //converting to miles
    }

    public boolean budgetSatisfies(int propertyCost,int searchMinCost,int searchMaxCost,int thresh){

        int minCostDiff = Math.abs(propertyCost - searchMinCost);
        int maxCostDiff = Math.abs(propertyCost - searchMaxCost);

        double validDifference;
        double validBaseCost;
        if(minCostDiff < maxCostDiff){
            validDifference = minCostDiff;
            validBaseCost = searchMinCost;
        }else{
            validDifference = maxCostDiff;
            validBaseCost = searchMaxCost;
        }

        if(validDifference/validBaseCost*100.0 <= thresh){
            return true;

        }

        return false;

    }

    /**
     * onverting logic of following sentence
     * bedroom and bathroom should be +/- 2.
     * @param propertyRoom
     * @param searchMinRoom
     * @param searchMaxRoom
     * @return
     */
    public boolean roomSatisfies(int propertyRoom, int searchMinRoom,int searchMaxRoom ){

        int minRoomDiff = Math.abs(propertyRoom - searchMinRoom);
        int maxRoomDiff = Math.abs(propertyRoom - searchMaxRoom);

        if(Math.min(minRoomDiff,maxRoomDiff) <=2 ){
            return true;
        }
        return false;

    }

    //code to extract match percentage

    /**
     * Converting logic of following sentence
     * If min or max is not given, match percentage varies according to the value.
     * @param propertyRoom
     * @param searchMinRoom
     * @param searchMaxRoom
     * @return
     */
    public double getRoomMatchPercentage(int propertyRoom,int searchMinRoom,int searchMaxRoom){

        double validBaseSearch;
        double difference;
        if(Math.abs(propertyRoom-searchMinRoom) < Math.abs(propertyRoom-searchMaxRoom)){
            validBaseSearch = searchMinRoom;
            difference = Math.abs(propertyRoom - searchMinRoom);
        }else{
            validBaseSearch = searchMaxRoom;
            difference = Math.abs(propertyRoom-searchMaxRoom);
        }

        return difference/validBaseSearch*100.0;
    }


}
