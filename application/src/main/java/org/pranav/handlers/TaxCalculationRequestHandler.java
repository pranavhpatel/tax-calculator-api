package org.pranav.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pranav.dataObjects.TaxCalculationRequest;
import org.pranav.implementors.TaxCalculator;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

import static spark.Spark.halt;


public class TaxCalculationRequestHandler {

    public static Route taxCalculationRequestHandler = (Request req, Response res) -> {
        try { //main try catch for error catching
            ObjectMapper objMapper = new ObjectMapper();

            // The decision to get income and year in JSON body is because income is considered sensitive information
            // and as such should be sent in a secure method which is not easily seen like in query parameters
            TaxCalculationRequest taxReq = objMapper.readValue(req.body(), TaxCalculationRequest.class);

            Map<String, String> taxes = TaxCalculator.calculateTaxes(taxReq.getIncome(), taxReq.getYear());

            res.type("application/json");
            return taxes;
        } catch (Exception e) {
            res.status(500);
            halt(500, "Whoops! Pranavs free tax calculator is not working atm, please try again!");
            throw e;
        }
    };

}
