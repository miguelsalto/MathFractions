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
    }

    @Test
    public void testEqualsForDifferentFractions() {
        Fraction fraction1 = new MixedFraction(1, 2, 1);
        Fraction fraction2 = new MixedFraction(3, 2, 1);
        assertThat(fraction1, not(fraction2));
    }

}
