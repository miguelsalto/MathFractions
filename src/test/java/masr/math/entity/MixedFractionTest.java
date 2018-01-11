package masr.math.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class MixedFractionTest {
    @Test
    public void testEqualsForEquivalentFractions() {
        assertThat(new MixedFraction(1, 2, 3),
                is(new MixedFraction(1, 2, 3)));
        assertThat(new MixedFraction(1, 1, 4), is(new MixedFraction(1.25)));
        assertThat(new MixedFraction(2.255), is(new MixedFraction(2, 51, 200)));
    }

    @Test
    public void testEqualsForDifferentFractions() {
        Fraction fraction1 = new MixedFraction(1, 2, 1);
        Fraction fraction2 = new MixedFraction(3, 2, 1);
        assertThat(fraction1, not(fraction2));
    }

    @Test
    public void testCorrectWholeNumberWhenCreatingFractionByDecimal() {
        assertThat(new MixedFraction(0.333).getWholeNumber(), is(0));
        assertThat(new MixedFraction(2.25).getWholeNumber(), is(2));
    }
}
