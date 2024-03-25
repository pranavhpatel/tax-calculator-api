import org.junit.jupiter.api.Test;
import org.pranav.dataObjects.TaxCalculationRequest;

import static org.junit.Assert.assertEquals;

public class TaxCalculationRequestTest {
    @Test
    public void testTaxCalculationRequest() {
        TaxCalculationRequest taxCalculationRequest = new TaxCalculationRequest();

        // Test setting and getting income
        taxCalculationRequest.setIncome(50000.0);
        assertEquals(50000.0, taxCalculationRequest.getIncome(), 0.01);

        // Test setting and getting year
        taxCalculationRequest.setYear(2024);
        assertEquals(2024, taxCalculationRequest.getYear());
    }
}
