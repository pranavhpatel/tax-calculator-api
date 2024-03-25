package org.pranav.implementors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pranav.handlers.DownStreamDataLoader;
import org.pranav.handlers.TaxBracketResponseHandler;

import java.io.IOException;
import java.util.Map;

import static org.pranav.implementors.TaxCalculatorImpl.calculateTaxesImpl;


public class TaxCalculator {
    public static Map<String, String> calculateTaxes(double income, int year) throws IOException {
        // Get the tax bracket info associated for the year
        // add try/catch to catch and handle errors accordingly
        try{
            String jsonResponse = DownStreamDataLoader.getResponse().toString();
            // Now parse the JSON response into TaxBracketResponseHandler object using Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            TaxBracketResponseHandler taxBracketResponse = objectMapper.readValue(jsonResponse, TaxBracketResponseHandler.class);
            return calculateTaxesImpl(income, taxBracketResponse);
        } catch (Exception e){
            System.out.println("caught error while calculating taxes: " + e.getMessage());
            throw e;
        }
    }


//     local testing main
    public static void main(String[] args) {
        try {
            double income = 1234567; // Example income
            int year = 2022; // Example year
            Map<String, String> taxes = calculateTaxes(income, year);
            System.out.println("Taxes:\n" + taxes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
