package masr.math.vo;

import java.io.Serializable;

public class FractionVO implements Serializable {
    private static final long serialVersionUID = -3176426989855983674L;

    private boolean correct;
    private String numerator;
    private String denominator;
    private String wholeNumber;
    private String decimal;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }

    public String getWholeNumber() {
        return wholeNumber;
    }

    public void setWholeNumber(String wholeNumber) {
        this.wholeNumber = wholeNumber;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }
}
