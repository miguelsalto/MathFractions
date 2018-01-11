package masr.math.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class Fraction implements Serializable {
    private static final long serialVersionUID = 3880523046719307150L;

    private static final int PRIME_FACTOR = 31;
    private static final String INTEGER_AND_POINT_REGEX = "\\d*\\.";

    private int numerator;
    private int denominator;
    private double decimal;

    public Fraction(int numerator, int denominator) {
        this.denominator = denominator;
        setNumerator(numerator);
        setDecimal(1d * numerator / denominator);
    }

    public Fraction(double decimal) {
        setDecimal(decimal);
        calculateNumeratorAndDenominator();
        reduceFraction();
    }

    private void calculateNumeratorAndDenominator() {
        String decimalNumber = removeIntegerAndPoint(Double.toString(decimal));
        denominator = calculateDenominator(decimalNumber.length());
        setNumerator((int) decimal * denominator + Integer.parseInt(decimalNumber));
    }

    private void reduceFraction() {
        int gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValue();
        numerator /= gcd;
        denominator /= gcd;
    }

    private String removeIntegerAndPoint(String text) {
        return text.replaceAll(INTEGER_AND_POINT_REGEX, "");
    }

    private int calculateDenominator(int length) {
        int calculated = 1;
        for (int i = 0; i < length; i++) {
            calculated *= 10;
        }
        return calculated;
    }

    void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    void setDecimal(double decimal) {
        this.decimal = decimal;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public double getDecimal() {
        return decimal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Fraction that = (Fraction) obj;
        return numerator == that.numerator && denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return getPrimeFactor() * numerator + denominator;
    }

    int getPrimeFactor() {
        return PRIME_FACTOR;
    }

}
