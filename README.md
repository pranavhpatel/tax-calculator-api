# tax-calculator-api

This is a free tax calculator app made by Pranav

This readme will go over how to start this application and make a request to it

1. Go to ApplicationRunner and start the application (edit port number to your liking)
2. Go to Postman or any other software and make a POST request to localhost:port/calculate-taxes with the body 

'{
   "income": ENTER_INCOME,
   "year": ENTER_YEAR
}'
3. Add basic auth to the request and enter username = "pranav" and password = "password"
4. If you want to configure downstream api properties go to qa-properties.properties and update the values accordingly. The code base uses assessment.url so if you want to update to anything else update that value then run application!

## Decisions For Input
The income and year will be inputs as JSON objects in the body of the request because that is sensitive information and should not be exposed in the query/url parameters

## Logging

Logger class can be created and implemented to send key info to third party sources like Splunk but not implemented here :(

## Tests

Unit tests are a must and integration tests and a lot of other tests.
This submission has tests for many key classes including the data loader to get the JSON tax brackets and few of the object classes and the main logic implementation class.


This is the work output from few hours of coding, with more time many many many changes and improvements can be done