package org.pranav;

import java.util.Base64;

import static org.pranav.handlers.TaxCalculationRequestHandler.taxCalculationRequestHandler;
import static spark.Spark.*;

public class ApplicationRunner {

    public static void main(String[] args) {
        // Set port number
        port(8080);

        // Adding some authorization for security, for real project this will be more focused and can have different microservices for token generation and verification
        final String credentials = "pranav:password"; // TEMP credentials for testing
        final String endcodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        before((req, res) -> {
            String authHeader = req.headers("Authorization");
            if (authHeader == null || !authHeader.equals("Basic " + endcodedCredentials)) {
                halt(401, "STOP! You are unauthorized to use Pranavs free tax calculator! If you really want to use it change your request Authorization to basic and add username and password as 'pranav' and 'password' ;)");
            }
                });

        // Define POST endpoint for /calculate-taxes
        try {
            post("/calculate-taxes", "application/json", taxCalculationRequestHandler);
        } catch (Exception e) {
            halt(500, "Pranavs free tax calculator is not working atm please try again!");
        }
    }
}
