package masr.math.controller;

import masr.math.entity.Fraction;
import masr.math.validator.FractionValidator;
import masr.math.vo.AnswerVO;
import masr.math.vo.ExerciseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static masr.math.constants.AppConstants.EXERCISE_ATTR_NAME;
import static masr.math.constants.AppConstants.NUMBER_OF_PROBLEMS;
import static masr.math.util.FractionGeneratorUtil.generateProperAndImproperFractions;
import static masr.math.validator.FractionValidator.isValidInteger;

@Controller
@SessionAttributes({EXERCISE_ATTR_NAME})
public class DecimalToFractionController extends BaseController<Fraction> {
    private static final String VIEW_NAME = "decimalToFraction";

    private final FractionValidator fractionValidator;

    @Autowired
    public DecimalToFractionController(FractionValidator fractionValidator) {
        this.fractionValidator = fractionValidator;
    }

    @RequestMapping(value = "/decimalToFraction", method = RequestMethod.GET)
    public ModelAndView showPage() {
        return createModelAndView(VIEW_NAME);
    }

    @Override
    List<Fraction> generateFractions() {
        return generateProperAndImproperFractions(NUMBER_OF_PROBLEMS);
    }

    @RequestMapping(value = "/decimalToFraction/grade", method = RequestMethod.POST)
    public String grade(@Validated @ModelAttribute(EXERCISE_ATTR_NAME) ExerciseVO<Fraction> exerciseVO, BindingResult result,
                        ModelMap modelMap) {
        updateModelForGrading(exerciseVO, result, modelMap);
        return VIEW_NAME;
    }

    @RequestMapping(value = "/decimalToFraction/verify", method = RequestMethod.POST)
    public String verify(@ModelAttribute(EXERCISE_ATTR_NAME) ExerciseVO<Fraction> exerciseVO, @SuppressWarnings("unused") BindingResult result,
                         ModelMap modelMap) {
        updateModelForVerifying(exerciseVO, modelMap);
        return VIEW_NAME;
    }

    @Override
    boolean isAnswerCompleteForEvaluation(AnswerVO answer) {
        return isNumeratorAndDenominatorDefined(answer);
    }

    private boolean isNumeratorAndDenominatorDefined(AnswerVO answer) {
        return isValidInteger(answer.getNumerator()) && isValidInteger(answer.getDenominator());
    }

    @Override
    boolean isCorrect(Fraction fraction, AnswerVO answer) {
        return isCorrectNumeratorAndDenominator(fraction, answer);
    }

    @Override
    Validator getValidator() {
        return fractionValidator;
    }
}
