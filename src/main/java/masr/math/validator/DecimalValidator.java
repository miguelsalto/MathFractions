package masr.math.validator;

import masr.math.vo.AnswerVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class DecimalValidator extends BaseValidator {
    @Override
    void validate(int idx, Errors errors, List<AnswerVO> answers) {
        String decimalField = getDecimalField(idx);
        rejectIfEmptyOrWhitespace(errors, decimalField, getErrorEmptyDecimal());

        AnswerVO answer = answers.get(idx);
        validateDecimal(errors, decimalField, answer.getDecimal());
    }
}
