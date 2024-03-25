import org.junit.jupiter.api.Test;
import org.pranav.dataObjects.TaxBracket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxBracketTest {
    @Test
    public void testTaxBracket() {
        TaxBracket taxBracket = new TaxBracket();

        // Test setting and getting min
        taxBracket.setMin(10000.0);
        assertEquals(10000.0, taxBracket.getMin(), 0.01);

        // Test setting and getting max
        taxBracket.setMax(20000);
        assertEquals(20000, taxBracket.getMax());

        // Test setting and getting rate
        taxBracket.setRate(0.25);
        assertEquals(0.25, taxBracket.getRate(), 0.01);

        // Test toString method
        assertEquals("TaxBracket{max=20000, min=10000.0, rate=0.25}", taxBracket.toString());
    }
}
