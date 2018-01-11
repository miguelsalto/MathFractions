package masr.math.util;

import masr.math.entity.Fraction;
import org.junit.Test;

import static masr.math.util.FractionGeneratorUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertTrue;


public class FractionGeneratorUtilTest {

    private static final int LOOPS = 10;

    @Test
    public void testValidRangeForNumeratorWhenGeneratingFraction() {
        for (int i = 0; i < LOOPS; i++) {
            assertTrue(isFractionNumberInRange(generateProperFraction().getNumerator()));
            assertTrue(isFractionNumberInRange(generateImproperFraction().getNumerator()));
            assertTrue(isFractionNumberInRange(generateProperFractionFromDecimal().getNumerator()));
        }
    }

    @Test
    public void testValidRangeForDenominatorWhenGeneratingFraction() {
        for (int i = 0; i < LOOPS; i++) {
            assertTrue(isFractionNumberInRange(generateProperFraction().getDenominator()));
            assertTrue(isFractionNumberInRange(generateImproperFraction().getDenominator()));
            assertTrue(isDenominatorByDecimalInRange(generateProperFractionFromDecimal().getDenominator()));
            assertTrue(isDenominatorByDecimalInRange(generateImproperFractionFromDecimal().getDenominator()));
        }
    }

    @Test
    public void testRandomGenerationOfFractions() {
        for (int i = 0; i < LOOPS; i++) {
            assertThat(generateProperFraction(), not(generateProperFraction()));
            assertThat(generateImproperFraction(), not(generateImproperFraction()));
            assertThat(generateProperFractionFromDecimal(), not(generateProperFractionFromDecimal()));
            assertThat(generateImproperFractionFromDecimal(), not(generateImproperFractionFromDecimal()));
        }
    }

    @Test
    public void testImproperFractionCreation() {
        for (int i = 0; i < LOOPS; i++) {
            Fraction fraction = generateImproperFraction();
            assertThat(fraction.getNumerator(), greaterThan(fraction.getDenominator()));
        }
    }

    @Test
    public void testImproperFractionCreationFromDecimal() {
        for (int i = 0; i < LOOPS; i++) {
            Fraction fraction = generateImproperFractionFromDecimal();
            assertThat(fraction.getNumerator(), greaterThan(fraction.getDenominator()));
        }
    }

    @Test
    public void testProperFractionCreation() {
        for (int i = 0; i < LOOPS; i++) {
            Fraction fraction = generateProperFraction();
            assertThat(fraction.getNumerator(), lessThan(fraction.getDenominator()));
        }
    }

    @Test
    public void testProperFractionCreationFromDecimal() {
        for (int i = 0; i < LOOPS; i++) {
            Fraction fraction = generateProperFractionFromDecimal();
            assertThat(fraction.getNumerator(), lessThan(fraction.getDenominator()));
        }
    }

    private static boolean isFractionNumberInRange(int value) {
        return isInRange(value);
    }

    private static boolean isDenominatorByDecimalInRange(int value) {
        return isInRange(value, 1);
    }

    private static boolean isInRange(int value) {
        return isInRange(value, 0);
    }

    private static boolean isInRange(int value, int maxOffset) {
        return value >= 1 && value <= (MAX_VALUE_FOR_DEFAULT_FRACTIONS + maxOffset);
    }

}
