package org.pranav.handlers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pranav.dataObjects.TaxBracket;

import java.util.List;

// Object class for holding taxbracket json response data
// annotation used as measure to ignore unknowns during JSON parsing making it more robust and flexible
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxBracketResponseHandler {

    // list of taxbrackets
    @JsonProperty("tax_brackets")
    private List<TaxBracket> taxBrackets;

    public List<TaxBracket> getTaxBrackets() {
        return taxBrackets;
    }

    public void setTaxBrackets(List<TaxBracket> taxBrackets){
        this.taxBrackets = taxBrackets;
    }

}
