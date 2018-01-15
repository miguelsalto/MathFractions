package masr.math.controller;

import masr.math.entity.Fraction;
import masr.math.entity.MixedFraction;
import masr.math.vo.AnswerVO;
import masr.math.vo.ExerciseVO;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static masr.math.constants.AppConstants.EXERCISE_ATTR_NAME;
import static masr.math.constants.AppConstants.NUMBER_OF_PROBLEMS;

abstract class BaseController<T extends Fraction> {
    private static final BigDecimal PRECISION_FACTOR = BigDecimal.valueOf(1000);

    abstract List<T> generateFractions();

    abstract Validator getValidator();

    abstract boolean isAnswerCompleteForEvaluation(AnswerVO answer);

    abstract boolean isCorrect(T fraction, AnswerVO answer);

    @SuppressWarnings("unused") // Invoked by Spring Boot
    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setValidator(getValidator());
    }

    ModelAndView createModelAndView(String viewName) {
        ExerciseVO exercise = createExercise();
        Collections.shuffle(exercise.getFractions());
        return new ModelAndView(viewName, EXERCISE_ATTR_NAME, exercise);
    }

    private ExerciseVO<T> createExercise() {
        ExerciseVO<T> exerciseVO = new ExerciseVO<>();
        exerciseVO.setFractions(generateFractions());
        exerciseVO.setResult(initFractionsVO());
        return exerciseVO;
    }

    private List<AnswerVO> initFractionsVO() {
        List<AnswerVO> emptyFractions = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            emptyFractions.add(new AnswerVO());
        }
        return emptyFractions;
    }

    void updateModelForGrading(ExerciseVO<T> exerciseVO, BindingResult result, ModelMap modelMap) {
        if (!result.hasErrors()) {
            markAsGraded(modelMap);
            int correctAnswers = evaluateAnswers(exerciseVO, false);
            modelMap.addAttribute("score", correctAnswers * 10.0 / NUMBER_OF_PROBLEMS);
        }
    }

    void updateModelForVerifying(ExerciseVO<T> exerciseVO, ModelMap modelMap) {
        markAsGraded(modelMap);
        evaluateAnswers(exerciseVO, true);
    }

    private void markAsGraded(ModelMap modelMap) {
        modelMap.addAttribute("isGraded", true);
    }

    private int evaluateAnswers(ExerciseVO<T> exerciseVO, boolean optional) {
        int correctAnswers = 0;
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            T fraction = exerciseVO.getFractions().get(i);
            AnswerVO answer = exerciseVO.getResult().get(i);
            if (isCompleteForEvaluation(optional, answer)) {
                answer.setCorrect(isCorrect(fraction, answer));
                answer.setSolved(true);
            } else {
                answer.setSolved(false);
            }
            if (answer.isCorrect()) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    private boolean isCompleteForEvaluation(boolean optional, AnswerVO answer) {
        return !optional || isAnswerCompleteForEvaluation(answer);
    }

    boolean isCorrectNumeratorAndDenominator(Fraction fraction, AnswerVO answer) {
        return isSameNumerator(fraction, answer) && isSameDenominator(fraction, answer);
    }

    boolean isCorrectMixedNumber(MixedFraction fraction, AnswerVO answer) {
        return isSameWholeNumber(fraction, answer) && isCorrectNumeratorAndDenominator(fraction, answer);
    }

    boolean isCorrectDecimal(Fraction fraction, AnswerVO answer) {
        BigDecimal generatedDecimal = BigDecimal.valueOf(fraction.getDecimal()).multiply(PRECISION_FACTOR);
        BigDecimal userAnswer = new BigDecimal(answer.getDecimal()).multiply(PRECISION_FACTOR);
        return generatedDecimal.intValue() == userAnswer.intValue();
    }

    private boolean isSameDenominator(Fraction fraction, AnswerVO answer) {
        return fraction.getDenominator() == Integer.parseInt(answer.getDenominator().trim());
    }

    private boolean isSameNumerator(Fraction fraction, AnswerVO answer) {
        return fraction.getNumerator() == Integer.parseInt(answer.getNumerator().trim());
    }

    private boolean isSameWholeNumber(MixedFraction fraction, AnswerVO answer) {
        return fraction.getWholeNumber() == Integer.parseInt(answer.getWholeNumber().trim());
    }
}
