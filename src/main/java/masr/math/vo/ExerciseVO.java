package masr.math.vo;

import masr.math.entity.Fraction;

import java.io.Serializable;
import java.util.List;

public class ExerciseVO<T extends Fraction> implements Serializable {
    private static final long serialVersionUID = 5860733021531449228L;

    private List<T> fractions;
    private List<AnswerVO> result;

    public List<T> getFractions() {
        return fractions;
    }

    public void setFractions(List<T> fractions) {
        this.fractions = fractions;
    }

    public List<AnswerVO> getResult() {
        return result;
    }

    public void setResult(List<AnswerVO> result) {
        this.result = result;
    }
}
