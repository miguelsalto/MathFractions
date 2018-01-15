package masr.math.controller;

import masr.math.entity.MixedFraction;
import masr.math.validator.MixedFractionValidator;
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
import static masr.math.util.FractionGeneratorUtil.generateMixedFractionsFromDecimal;
import static masr.math.validator.FractionValidator.isValidInteger;

@Controller
@SessionAttributes({EXERCISE_ATTR_NAME})
public class DecimalToMixedController extends BaseController<MixedFraction> {
    private static final String VIEW_NAME = "decimalToMixed";

    private final MixedFractionValidator mixedFractionValidator;

    @Autowired
    public DecimalToMixedController(MixedFractionValidator mixedFractionValidator) {
        this.mixedFractionValidator = mixedFractionValidator;
    }

    @RequestMapping(value = "/decimalToMixed", method = RequestMethod.GET)
    public ModelAndView showPage() {
        return createModelAndView(VIEW_NAME);
    }

    @Override
    List<MixedFraction> generateFractions() {
        return generateMixedFractionsFromDecimal(NUMBER_OF_PROBLEMS);
    }

    @RequestMapping(value = "/decimalToMixed/grade", method = RequestMethod.POST)
    public String grade(@Validated @ModelAttribute(EXERCISE_ATTR_NAME) ExerciseVO<MixedFraction> exerciseVO, BindingResult result,
                        ModelMap modelMap) {
        updateModelForGrading(exerciseVO, result, modelMap);
        return VIEW_NAME;
    }

    @RequestMapping(value = "/decimalToMixed/verify", method = RequestMethod.POST)
    public String verify(@ModelAttribute(EXERCISE_ATTR_NAME) ExerciseVO<MixedFraction> exerciseVO, @SuppressWarnings("unused") BindingResult result,
                         ModelMap modelMap) {
        updateModelForVerifying(exerciseVO, modelMap);
        return VIEW_NAME;
    }

    @Override
    boolean isAnswerCompleteForEvaluation(AnswerVO answer) {
        return areAllIntegersDefined(answer);
    }

    private boolean areAllIntegersDefined(AnswerVO answer) {
        return isValidInteger(answer.getWholeNumber()) && isValidInteger(answer.getNumerator())
                && isValidInteger(answer.getDenominator());
    }

    @Override
    boolean isCorrect(MixedFraction fraction, AnswerVO answer) {
        return isCorrectMixedNumber(fraction, answer);
    }

    @Override
    Validator getValidator() {
        return mixedFractionValidator;
    }
}
