import org.junit.jupiter.api.Test;
import org.pranav.dataObjects.TaxBracket;
import org.pranav.handlers.TaxBracketResponseHandler;
import org.pranav.implementors.TaxCalculatorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaxCalculatorImplTest {
    @Test
    public void testCalculateTaxesImpl() {
        // create 1 tax bracket and test it
        TaxBracketResponseHandler taxBracketResponse = new TaxBracketResponseHandler();
        List<TaxBracket> taxBrackets = new ArrayList<>();
        TaxBracket bracket1 = new TaxBracket();
        bracket1.setMin(0);
        bracket1.setMax(50000);
        bracket1.setRate(0.1);
        taxBrackets.add(bracket1);

        taxBracketResponse.setTaxBrackets(taxBrackets);

        Map<String, String> result = TaxCalculatorImpl.calculateTaxesImpl(50000.0, taxBracketResponse);

        assertEquals("5000.00", result.get("\nBracket: 0.0-50000.0"));
    }
}
