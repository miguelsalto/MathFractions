package masr.math.entity;

public class MixedFraction extends Fraction {
    private static final long serialVersionUID = -9197017342962847567L;

    private final int wholeNumber;

    public MixedFraction(int wholeNumber, int numerator, int denominator) {
        super(getMixedNumerator(wholeNumber, numerator, denominator), denominator);
        this.wholeNumber = wholeNumber;
        setNumerator(numerator);
    }

    private static int getMixedNumerator(int wholeNumber, int numerator, int denominator) {
        return denominator * wholeNumber + numerator;
    }

    @SuppressWarnings("unused") //Invoked by view
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
