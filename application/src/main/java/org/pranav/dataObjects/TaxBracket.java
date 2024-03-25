package org.pranav.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Object class for the tax bracket qa-properties.properties
// annotation used as measure to ignore unknowns during JSON parsing making it more robust and flexible
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxBracket {
    private double min;
    private Integer max; // Integer max and not int to handle larger values and null max value cases
    private double rate;

    // Getters and Setters
    // Ideally they can be created using lombok and don't need to be typed out
    public double getMin(){
        return min;
    }

    public void setMin(double min){
        this.min =min;
    }

    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public double getRate(){
        return rate;
    }

    public void setRate(double rate){
        this.rate = rate;
    }

    // for reading while testing
    @Override
    public String toString() {
        return "TaxBracket{" +
                "max=" + max +
                ", min=" + min +
                ", rate=" + rate +
                '}';
    }

}
