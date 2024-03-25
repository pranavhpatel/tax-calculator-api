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
            int min = currentBracket.getMin();
            Integer max = currentBracket.getMax();
            double rate = currentBracket.getRate();

            // calculate income which will be taxed for current bracket
            // get income spread using max - min, but if max is null it means no more cap (last bracket) so use infinity as upper bound
            double incomeToTax = Math.min(max != null ? max - min : Double.POSITIVE_INFINITY, remainingIncome - min);
//          System.out.println("incomeSpread: " + incomeSpread + ", incomeRemaining" + remainingIncome);


            // calculate income tax for this bracket using incomeToTax
            double taxesThisBracket = incomeToTax * rate;
            taxesPerBracket.put("\nBracket: " + min + "-" + (max != null? max : "Max Bracket"), String.format("%.2f",taxesThisBracket));
            totalTaxes += taxesThisBracket;

            remainingIncome -= incomeToTax;
            // end loop if all income is taxed
            if (remainingIncome < 1) {
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
