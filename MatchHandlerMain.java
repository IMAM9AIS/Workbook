package com.im.tut;

import com.im.tut.models.Property;
import com.im.tut.models.SearchCriteria;
import com.im.tut.utils.PropertyMatchUtil;

import java.util.ArrayList;

public class MatchHandlerMain {

    public static void main(String args[]) throws Exception {


        ArrayList<Property> allProperties = new ArrayList<>();
        Property p1 = new Property(2,20.001,30,3,3,50);
        Property p2 = new Property(3,100,100,6,9,5000);
        allProperties.add(p1);
        allProperties.add(p2);

        SearchCriteria searchCriteria = new SearchCriteria(1,20,30,40,100,2,4,2,4);


        // a linear search over all properties will fulfill around 1sec time for a million entires;

        // for faster search on a 2 plane(considering we are taking into acoount longitude and latitude we can have clusters of data
        // and perform basic search on clusters first and eliminate possible bad clusters.
        // then dive depper into the clusters

        ArrayList<Property> validMatches = new ArrayList<>();
        ArrayList<Double> percentageMatch = new ArrayList<>();
        for(Property property:allProperties){
            PropertyMatchUtil propertyMatchUtil = new PropertyMatchUtil(property,searchCriteria);
            if(propertyMatchUtil.validMatch()){
                validMatches.add(property);
                percentageMatch.add(propertyMatchUtil.getMatchPercentage());
            }
        }


        for(Property property:validMatches){
            System.out.println(property.getId());
        }
    }


}
