package masr.math.validator;

import org.junit.Test;

import static masr.math.validator.BaseValidator.isValidDecimal;
import static masr.math.validator.BaseValidator.isValidInteger;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BaseValidatorTest {
    @Test
    public void testIsValidInteger() throws Exception {
        assertFalse(isValidInteger(null));
        assertFalse(isValidInteger(""));
        assertFalse(isValidInteger(" "));
        assertFalse(isValidInteger("1a"));
        assertTrue(isValidInteger("12"));
        assertTrue(isValidInteger(" 12 "));
    }

    @Test
    public void testIsValidDecimal() throws Exception {
        assertFalse(isValidDecimal(null));
        assertFalse(isValidDecimal(""));
        assertFalse(isValidDecimal(" "));
        assertFalse(isValidDecimal(" 1a"));
        assertFalse(isValidDecimal(" 1.1."));
        assertTrue(isValidDecimal(" 1.23 "));
        assertTrue(isValidDecimal(" .23"));
        assertTrue(isValidDecimal(" 23."));
    }

}