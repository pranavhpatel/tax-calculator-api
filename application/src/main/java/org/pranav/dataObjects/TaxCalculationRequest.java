package org.pranav.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxCalculationRequest {
    private double income;
    private int year;

    // Getters and Setters
    // Ideally they can be created using lombok and don't need to be typed out
    public double getIncome(){
        return income;
    }

    public void setIncome(double income){
        this.income = income;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }
}
