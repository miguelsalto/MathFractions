package masr.math.entity;

import java.math.BigDecimal;

public class MixedFraction extends Fraction {
    private static final long serialVersionUID = -9197017342962847567L;

    private int wholeNumber;

    public MixedFraction(int wholeNumber, int numerator, int denominator) {
        super(getMixedNumerator(wholeNumber, numerator, denominator), denominator);
        this.wholeNumber = wholeNumber;
        setNumerator(numerator);
    }

    public MixedFraction(double decimal) {
        super(getDecimalWithoutInteger(decimal));
        wholeNumber = (int) decimal;
        setDecimal(decimal);
    }

    static int getMixedNumerator(int wholeNumber, int numerator, int denominator) {
        return denominator * wholeNumber + numerator;
    }

    private static double getDecimalWithoutInteger(double value) {
        BigDecimal decimal = BigDecimal.valueOf(value);
        return decimal.subtract(BigDecimal.valueOf(decimal.intValue())).doubleValue();
    }

    public int getWholeNumber() {
        return wholeNumber;
    }

    @Override
    public boolean equals(Object that) {
        return super.equals(that) && wholeNumber == ((MixedFraction) that).wholeNumber;

    }

    @Override
    public int hashCode() {
        return getPrimeFactor() * super.hashCode() + wholeNumber;
    }
}
