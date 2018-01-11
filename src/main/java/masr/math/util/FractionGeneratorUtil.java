package masr.math.util;

import masr.math.entity.Fraction;

import java.util.Random;

public class FractionGeneratorUtil {

    private static final Random RANDOM = new Random();
    static final int MAX_VALUE_FOR_DEFAULT_FRACTIONS = 999;
    private static final double MAX_DENOMINATOR_FROM_DECIMAL = 1000.0;

    public static Fraction generateImproperFraction() {
        int numerator = generateInt();
        int denominator = generateInt(numerator - 1);
        return new Fraction(numerator, denominator);
    }

    public static Fraction generateProperFraction() {
        int numerator = getNumeratorDifferentToMax();
        int denominator = generateInt(numerator + 1, MAX_VALUE_FOR_DEFAULT_FRACTIONS);
        return new Fraction(numerator, denominator);
    }

    public static Fraction generateImproperFractionFromDecimal() {
        return new Fraction(generateInt() + generateDecimal());
    }

    public static Fraction generateProperFractionFromDecimal() {
        return new Fraction(generateDecimal());
    }

    private static int getNumeratorDifferentToMax() {
        int numerator = MAX_VALUE_FOR_DEFAULT_FRACTIONS;
        while (numerator == MAX_VALUE_FOR_DEFAULT_FRACTIONS) {
            numerator = generateInt();
        }
        return numerator;
    }

    static double generateDecimal() {
        return generateInt(MAX_VALUE_FOR_DEFAULT_FRACTIONS) / MAX_DENOMINATOR_FROM_DECIMAL;
    }

    private static int generateInt() {
        return generateInt(1, MAX_VALUE_FOR_DEFAULT_FRACTIONS);
    }

    private static int generateInt(int maxValue) {
        return generateInt(1, maxValue);
    }

    private static int generateInt(int minValue, int maxValue) {
        return RANDOM.nextInt(maxValue - minValue + 1) + minValue;
    }
}
