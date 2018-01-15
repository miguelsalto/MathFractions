package masr.math.util;

import masr.math.entity.Fraction;
import masr.math.entity.MixedFraction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public final class FractionGeneratorUtil {

    private static final Random RANDOM = new Random();
    static final int MAX_VALUE_FOR_DEFAULT_FRACTIONS = 999;
    private static final BigDecimal MAX_DENOMINATOR_FROM_DECIMAL = BigDecimal.valueOf(1000);

    private static final Supplier<Fraction> PROPER_FRACTION_SUPPLIER = FractionGeneratorUtil::generateProperFractionFromDecimal;
    private static final Supplier<Fraction> IMPROPER_FRACTION_SUPPLIER = FractionGeneratorUtil::generateImproperFractionFromDecimal;
    private static final Supplier<MixedFraction> MIXED_BY_INTEGER_SUPPLIER = FractionGeneratorUtil::generateMixedFraction;
    private static final Supplier<MixedFraction> MIXED_BY_DECIMAL_SUPPLIER = FractionGeneratorUtil::generateMixedFractionFromDecimal;

    private FractionGeneratorUtil() {
    }

    public static List<Fraction> generateProperAndImproperFractions(int totalSize) {
        List<Fraction> fractions = generateFractions(totalSize, PROPER_FRACTION_SUPPLIER);
        fractions.addAll(generateFractions(totalSize, IMPROPER_FRACTION_SUPPLIER));
        return fractions;
    }

    private static List<Fraction> generateFractions(int totalSize, Supplier<Fraction> supplier) {
        List<Fraction> properFractions = new ArrayList<>();
        for (int i = 0; i < totalSize / 2; i++) {
            properFractions.add(supplier.get());
        }
        return properFractions;
    }

    public static List<MixedFraction> generateMixedFractions(int totalSize) {
        return generateMixedFractions(totalSize, MIXED_BY_INTEGER_SUPPLIER);
    }

    public static List<MixedFraction> generateMixedFractionsFromDecimal(int totalSize) {
        return generateMixedFractions(totalSize, MIXED_BY_DECIMAL_SUPPLIER);
    }

    private static List<MixedFraction> generateMixedFractions(int totalSize, Supplier<MixedFraction> supplier) {
        List<MixedFraction> mixedFractions = new ArrayList<>();
        for (int i = 0; i < totalSize; i++) {
            mixedFractions.add(supplier.get());
        }
        return mixedFractions;
    }

    static Fraction generateImproperFraction() {
        int numerator = generateInt();
        int denominator = generateInt(numerator - 1);
        return new Fraction(numerator, denominator);
    }

    static Fraction generateProperFraction() {
        int numerator = getNumeratorDifferentToMax();
        int denominator = generateInt(numerator + 1, MAX_VALUE_FOR_DEFAULT_FRACTIONS);
        return new Fraction(numerator, denominator);
    }

    static Fraction generateImproperFractionFromDecimal() {
        double sum = BigDecimal.valueOf(generateInt()).add(BigDecimal.valueOf(generateDecimal())).doubleValue();
        return new Fraction(sum);
    }

    static Fraction generateProperFractionFromDecimal() {
        return new Fraction(generateDecimal());
    }

    private static MixedFraction generateMixedFraction() {
        Fraction proper = generateProperFraction();
        return new MixedFraction(generateInt(), proper.getNumerator(), proper.getDenominator());
    }

    private static MixedFraction generateMixedFractionFromDecimal() {
        Fraction proper = generateProperFractionFromDecimal();
        return new MixedFraction(generateInt(), proper.getNumerator(), proper.getDenominator());
    }

    private static int getNumeratorDifferentToMax() {
        int numerator = MAX_VALUE_FOR_DEFAULT_FRACTIONS;
        while (numerator == MAX_VALUE_FOR_DEFAULT_FRACTIONS) {
            numerator = generateInt();
        }
        return numerator;
    }

    private static double generateDecimal() {
        return removeFromTenThouandths(BigDecimal.valueOf(generateNonZeroDouble()));
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

    private static double generateNonZeroDouble() {
        double generated = 0;
        while (generated < 1e-3) {
            generated = RANDOM.nextDouble();
        }
        return generated;
    }

    private static double removeFromTenThouandths(BigDecimal number) {
        int hundreds = number.multiply(MAX_DENOMINATOR_FROM_DECIMAL).intValue();
        return BigDecimal.valueOf(hundreds).divide(MAX_DENOMINATOR_FROM_DECIMAL, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
