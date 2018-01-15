package masr.math.validator;

import masr.math.vo.AnswerVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class FractionValidator extends BaseValidator {

    @Override
    void validate(int idx, Errors errors, List<AnswerVO> answers) {
        String numeratorField = getNumeratorField(idx);
        String denominatorField = getDenominatorField(idx);

        rejectIfEmptyOrWhitespace(errors, numeratorField, getErrorEmptyInteger());
        rejectIfEmptyOrWhitespace(errors, denominatorField, getErrorEmptyInteger());

        AnswerVO answer = answers.get(idx);
        validateInteger(errors, numeratorField, answer.getNumerator());
        validateInteger(errors, denominatorField, answer.getDenominator());
    }

}
