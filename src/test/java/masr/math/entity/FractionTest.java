package masr.math.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class FractionTest {

    private static final double ERROR = 1e-3;

    @Test
    public void testEqualsForEquivalentFractions() {
        assertThat(new Fraction(0.2), is(new Fraction(1, 5)));
        assertThat(new Fraction(1, 2), is(new Fraction(0.5)));
        assertThat(new Fraction(1, 1), is(new Fraction(1, 1)));
    }

    @Test
    public void testEqualsForDifferentFractions() {
        Fraction fraction1 = new Fraction(1, 1);
        Fraction fraction2 = new Fraction(1, 2);
        assertThat(fraction1, not(fraction2));
    }

    @Test
    public void testCorrectDecimalWhenCreatingFraction() {
        Fraction fraction = new Fraction(1, 3);
        assertThat(fraction.getDecimal(), closeTo(0.3333, ERROR));
    }

    @Test
    public void testCorrectNumeratorWhenCreatingFractionByDecimal() {
        assertThat(new Fraction(0.333).getNumerator(), is(333));
        assertThat(new Fraction(0.25).getNumerator(), is(1));
    }

    @Test
    public void testCorrectDenominatorWhenCreatingFractionByDecimal() {
        assertThat(new Fraction(0.333).getDenominator(), is(1000));
        assertThat(new Fraction(0.25).getDenominator(), is(4));
    }

}