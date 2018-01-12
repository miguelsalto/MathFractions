package masr.math.validator;


import masr.math.vo.ExerciseVO;
import masr.math.vo.FractionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class DecimalToFractionValidator implements Validator {
    private static final String NUMERATOR_FIELD = "result[%d].numerator";
    private static final String DENOMINATOR_FIELD = "result[%d].denominator";
    private static final String ERROR_EMPTY_INTEGER = "error.empty.integer";

    @Override
    public boolean supports(Class<?> aClass) {
        return ExerciseVO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExerciseVO ex = (ExerciseVO) target;
        List<FractionVO> answers = ex.getResult();
        for (int idx = 0; idx < answers.size(); idx++) {
            String numeratorField = getNumeratorField(idx);
            String denominatorField = getDenominatorField(idx);

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, numeratorField, ERROR_EMPTY_INTEGER);
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, denominatorField, ERROR_EMPTY_INTEGER);

            FractionVO fraction = answers.get(idx);
            validateInteger(errors, numeratorField, fraction.getNumerator());
            validateInteger(errors, denominatorField, fraction.getDenominator());

        }
    }

    private static String getNumeratorField(int index) {
        return String.format(NUMERATOR_FIELD, index);
    }

    private static String getDenominatorField(int index) {
        return String.format(DENOMINATOR_FIELD, index);
    }

    private void validateInteger(Errors errors, String numeratorField, String field) {
        if (StringUtils.isNoneBlank(field)) {
            try {
                //noinspection ResultOfMethodCallIgnored
                Integer.parseInt(field.trim());
            } catch (NumberFormatException e) {
                errors.rejectValue(numeratorField, "error.incorrect.format");
            }
        }
    }


}
