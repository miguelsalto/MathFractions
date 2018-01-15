package masr.math.validator;

import masr.math.vo.AnswerVO;
import masr.math.vo.ExerciseVO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

public abstract class BaseValidator implements Validator {
    private static final String DECIMAL_FIELD = "result[%d].decimal";
    private static final String DENOMINATOR_FIELD = "result[%d].denominator";
    private static final String NUMERATOR_FIELD = "result[%d].numerator";
    private static final String WHOLE_NUMBER_FIELD = "result[%d].wholeNumber";

    private static final String ERROR_EMPTY_INTEGER = "error.empty.integer";
    private static final String ERROR_EMPTY_DECIMAL = "error.empty.decimal";

    abstract void validate(int idx, Errors errors, List<AnswerVO> answers);

    @Override
    public boolean supports(Class<?> aClass) {
        return ExerciseVO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExerciseVO<?> ex = (ExerciseVO<?>) target;
        List<AnswerVO> answers = ex.getResult();
        for (int idx = 0; idx < answers.size(); idx++) {
            validate(idx, errors, answers);
        }
    }

    void validateInteger(Errors errors, String numeratorField, String field) {
        if (isNotBlank(field) && !isValidInteger(field)) {
            errors.rejectValue(numeratorField, "error.incorrect.format");
        }
    }

    void validateDecimal(Errors errors, String numeratorField, String field) {
        if (isNotBlank(field) && !isValidDecimal(field)) {
            errors.rejectValue(numeratorField, "error.incorrect.format");
        }
    }

    static String getNumeratorField(int index) {
        return String.format(NUMERATOR_FIELD, index);
    }

    static String getDenominatorField(int index) {
        return String.format(DENOMINATOR_FIELD, index);
    }

    static String getDecimalField(int index) {
        return String.format(DECIMAL_FIELD, index);
    }

    static String getWholeNumberField(int index) {
        return String.format(WHOLE_NUMBER_FIELD, index);
    }

    public static boolean isValidInteger(String s) {
        return isNotBlank(s) && isNumeric(s.trim());
    }

    public static boolean isValidDecimal(String s) {
        if (isBlank(s)) {
            return false;
        }
        boolean valid = true;
        int points = 0;
        String trimmed = s.trim();
        for (int i = 0; valid && i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (c == '.') {
                points++;
                valid = points <= 1;
            } else {
                valid = Character.isDigit(c);
            }
        }
        return valid;
    }

    static String getErrorEmptyInteger() {
        return ERROR_EMPTY_INTEGER;
    }

    static String getErrorEmptyDecimal() {
        return ERROR_EMPTY_DECIMAL;
    }
}
