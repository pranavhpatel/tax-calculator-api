package org.pranav.implementors;

import org.pranav.dataObjects.TaxBracket;
import org.pranav.handlers.TaxBracketResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxCalculatorImpl {

    public static Map<String, String> calculateTaxesImpl(double income, TaxBracketResponseHandler taxBracketResponse) {
        List<TaxBracket> taxBrackets = taxBracketResponse.getTaxBrackets();
        // Calculate taxes by each tax bracket

        // reason for using hashmap is due to its performance capabilities
        // assuming there won't be crazy amounts of tax brackets so don't need to scale this part
        Map<String, String> taxesPerBracket = new HashMap<>();
        double remainingIncome = income; // will be dynamic as income by brackets is calculated and moved to next
        double totalTaxes = 0;


        // calculation per bracket in loop
        for (TaxBracket currentBracket : taxBrackets){
            double min = currentBracket.getMin();
            double max = currentBracket.getMax() != null? currentBracket.getMax() : Double.MAX_VALUE;
            double rate = currentBracket.getRate();
            double taxThisBracket = 0;

            // calculate income which will be taxed for current bracket
            double taxableAmount = Math.min(max - min , remainingIncome);
            if (taxableAmount > 0) {
                taxThisBracket = taxableAmount * rate;
                totalTaxes += taxThisBracket;
                remainingIncome -= taxableAmount;
                taxesPerBracket.put("\nBracket: " + min + "-" + max, String.format("%.2f",taxThisBracket));
            }
            if (remainingIncome < 1){
                break;
            }
        }

        // output taxes per bracket and total taxes for the year
        Map<String, String> taxCalculationResponse = new HashMap<>();
        taxCalculationResponse.put("\nTotal Taxes", String.format("%.2f", totalTaxes));
        taxCalculationResponse.putAll(taxesPerBracket);
        return taxCalculationResponse;
    }
}
