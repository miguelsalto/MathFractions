package masr.math.vo;

import java.io.Serializable;

@SuppressWarnings("unused") // Used by view
public class AnswerVO implements Serializable {
    private static final long serialVersionUID = 8455931749889615323L;

    private boolean correct;
    private boolean solved;

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

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
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
