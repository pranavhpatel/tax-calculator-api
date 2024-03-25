package org.pranav.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pranav.implementors.TaxCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class DownStreamDataLoader {

    public static StringBuilder getResponse () throws IOException {
        // Load properties from qa-properties.properties file
        Properties p = new Properties();
        // class level variables
        String propertiesFile = "qa-properties.properties";
        InputStream input = TaxCalculator.class.getClassLoader().getResourceAsStream(propertiesFile);
        p.load(input);

        // set loaded variables
        String host = p.getProperty("host");
        String port = p.getProperty("port");
        String taxBracketInfoDevelopEndpoint = p.getProperty("develop.url");
        String taxBracketInfoAssessEndpoint = p.getProperty("assessment.url");
        StringBuilder response = new StringBuilder();

        // Get the tax bracket info associated for the year
        // add try/catch to catch and handle errors accordingly
        try{
            ObjectMapper objMapper = new ObjectMapper();
            URL url = new URL("http://" + host + ":" + port + taxBracketInfoAssessEndpoint); // can improve with better url builder
//            URL url = new URL("http://localhost:5001/tax-calculator/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
//                System.out.println("JSON: " + jsonResponse);
            } else {
                System.out.println("Failed to get response from the endpoint. Response code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e){
            System.out.println("caught error while loading tax bracket information: " + e.getMessage());
        }
        return response;
    }
}
