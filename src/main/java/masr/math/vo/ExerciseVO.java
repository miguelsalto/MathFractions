package masr.math.vo;

import masr.math.entity.Fraction;

import java.io.Serializable;
import java.util.List;

public class ExerciseVO implements Serializable {
    private static final long serialVersionUID = 5860733021531449228L;

    private List<Fraction> fractions;
    private List<FractionVO> result;

    public List<Fraction> getFractions() {
        return fractions;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }

    public List<FractionVO> getResult() {
        return result;
    }

    public void setResult(List<FractionVO> result) {
        this.result = result;
    }
}
